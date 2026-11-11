package com.mirea.mycle.impl;

import com.mirea.mycle.api.VFSCore;
import org.w3c.dom.*;

import java.util.*;

public class VFSCoreImplXML extends VFSCore {
    private Map<String, Node> elementsByNameMap = new HashMap<>();
    private String[] vfsCurrentContent;
    private Node vfsRoot, vfsCurrent;

    private VFSCoreImplXML(Document vfsSource) {
        this.vfsRoot = vfsSource.getDocumentElement();
        this.vfsCurrent = vfsRoot;
        update();
    }

    @Override
    public boolean goToDirectory(String dirName) {
        if (Objects.equals(dirName, "..")) {
            if (vfsCurrent == vfsRoot) {
                return false;
            }
            vfsCurrent = vfsCurrent.getParentNode();
            update();
            return true;
        }
        if (Objects.equals(dirName, "/")) {
            vfsCurrent = vfsRoot;
            update();
            return true;
        }
        NodeList vfsChildren = vfsCurrent.getChildNodes();
        for (int i = 0; i < vfsChildren.getLength(); i++) {
            Node vfsElement = vfsChildren.item(i);
            if (vfsElement.getNodeType() != Node.ELEMENT_NODE) continue;

            String vfsElementName = vfsElement.getAttributes().getNamedItem("name").getNodeValue();
            if (Objects.equals(vfsElementName, dirName) && Objects.equals(vfsElement.getNodeName(), "directory")) {
                vfsCurrent = vfsElement;
                update();
                return true;
            }
        }
        return false;
    }

    private void update() {
        NodeList vfsChildren = vfsCurrent.getChildNodes();
        elementsByNameMap.clear();
        List<String> vfsCurrentContentTemp = new ArrayList<>(vfsChildren.getLength());
        for (int i = 0; i < vfsChildren.getLength(); i++) {
            Node vfsElement = vfsChildren.item(i);
            if (vfsElement.getNodeType() != Node.ELEMENT_NODE) continue;

            String vfsElementName = vfsElement.getAttributes().getNamedItem("name").getNodeValue();
            elementsByNameMap.put(vfsElementName, vfsElement);
            vfsCurrentContentTemp.add(vfsElementName);
        }
        vfsCurrentContent = vfsCurrentContentTemp.toArray(String[]::new);
    }

    @Override
    public String getCurrentDirectory() {
        return vfsCurrent.getAttributes().getNamedItem("name").getNodeValue();
    }

    @Override
    public String[] readDirectory() {
        return vfsCurrentContent;
    }

    @Override
    public String readFile(String fileName) {
        if (!elementsByNameMap.containsKey(fileName)) return null;
        Node file = elementsByNameMap.get(fileName);

        if (Objects.equals(file.getNodeName(), "file")) {
            return file.getAttributes().getNamedItem("content").getNodeValue();
        }
        return null;
    }

    @Override
    public boolean isFile(String fileName) {
        if (!elementsByNameMap.containsKey(fileName)) return false;
        Node vfsElement = elementsByNameMap.get(fileName);
        return Objects.equals(vfsElement.getNodeName(), "file");
    }

    public static class Instance {
        private static VFSCore instance;

        public static VFSCore create(Document vfsSource) {
            instance = new VFSCoreImplXML(vfsSource);
            return instance;
        }

        public static VFSCore get() {
            return instance;
        }
    }
}

/****************************************************************************/
// Eclipse SUMO, Simulation of Urban MObility; see https://eclipse.org/sumo
// Copyright (C) 2017-2019 German Aerospace Center (DLR) and others.
// TraaS module
// Copyright (C) 2016-2017 Dresden University of Technology
// This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v2.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v20.html
// SPDX-License-Identifier: EPL-2.0
/****************************************************************************/
/// @file    StringList.java
/// @author  Mario Krumnow
/// @author  Evamarie Wiessner
/// @date    2016
///
//
/****************************************************************************/
package traas.module.de.tudresden.sumo.util;

import traas.module.it.polito.appeal.traci.TraCIException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import traas.module.de.tudresden.sumo.config.Constants;
import traas.module.de.uniluebeck.itm.tcpip.Storage;

class StringList implements List<String> {

    private final List<String> list;

    public StringList() {
        list = new ArrayList<String>();
    }

    public StringList(List<String> list) {
        this.list = list;
    }

    public StringList(Storage storage, boolean verifyType) throws TraCIException {
        if (verifyType) {
            if (storage.readByte() != Constants.TYPE_STRINGLIST) {
                throw new TraCIException("string list expected");
            }
        }

        int len = storage.readInt();
        list = new ArrayList<String>(len);
        for (int i = 0; i < len; i++) {
            list.add(storage.readStringASCII());
        }
    }

    public void writeTo(Storage out, boolean writeTypeID) {
        if (writeTypeID) {
            out.writeByte(Constants.TYPE_STRINGLIST);
        }
        out.writeInt(list.size());
        for (String str : list) {
            out.writeStringASCII(str);
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }

    /**
     * @param index
     * @param element
     * @see List#add(int, Object)
     */
    public void add(int index, String element) {
        list.add(index, element);
    }

    /**
     * @param e

     * @see List#add(Object)
     */
    public boolean add(String e) {
        return list.add(e);
    }

    /**
     * @param c

     * @see List#addAll(Collection)
     */
    public boolean addAll(Collection<? extends String> c) {
        return list.addAll(c);
    }

    /**
     * @param index
     * @param c

     * @see List#addAll(int, Collection)
     */
    public boolean addAll(int index, Collection<? extends String> c) {
        return list.addAll(index, c);
    }

    /**
     *
     * @see List#clear()
     */
    public void clear() {
        list.clear();
    }

    /**
     * @param o

     * @see List#contains(Object)
     */
    public boolean contains(Object o) {
        return list.contains(o);
    }

    /**
     * @param c

     * @see List#containsAll(Collection)
     */
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    /**
     * @param o

     * @see List#equals(Object)
     */
    public boolean equals(Object o) {
        return list.equals(o);
    }

    /**
     * @param index

     * @see List#get(int)
     */
    public String get(int index) {
        return list.get(index);
    }

    /**

     * @see List#hashCode()
     */
    public int hashCode() {
        return list.hashCode();
    }

    /**
     * @param o

     * @see List#indexOf(Object)
     */
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    /**

     * @see List#isEmpty()
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**

     * @see List#iterator()
     */
    public Iterator<String> iterator() {
        return list.iterator();
    }

    /**
     * @param o

     * @see List#lastIndexOf(Object)
     */
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    /**

     * @see List#listIterator()
     */
    public ListIterator<String> listIterator() {
        return list.listIterator();
    }

    /**
     * @param index

     * @see List#listIterator(int)
     */
    public ListIterator<String> listIterator(int index) {
        return list.listIterator(index);
    }

    /**
     * @param index

     * @see List#remove(int)
     */
    public String remove(int index) {
        return list.remove(index);
    }

    /**
     * @param o

     * @see List#remove(Object)
     */
    public boolean remove(Object o) {
        return list.remove(o);
    }

    /**
     * @param c

     * @see List#removeAll(Collection)
     */
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    /**
     * @param c

     * @see List#retainAll(Collection)
     */
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    /**
     * @param index
     * @param element

     * @see List#set(int, Object)
     */
    public String set(int index, String element) {
        return list.set(index, element);
    }

    /**

     * @see List#size()
     */
    public int size() {
        return list.size();
    }

    /**
     * @param fromIndex
     * @param toIndex

     * @see List#subList(int, int)
     */
    public List<String> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    /**

     * @see List#toArray()
     */
    public Object[] toArray() {
        return list.toArray();
    }

    /**
     * @param <T>
     * @param a
     * @see List#toArray(Object[])
     */
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }


}

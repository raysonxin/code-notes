package com.raysonxin.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * @className: SetDemo.java
 * @author: raysonxin
 * @date: 2020/2/19 9:02 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class SetDemo {

    public static void main(String[] args) {
//        HashSet<String> hashSet = new HashSet<String>();
//        hashSet.add("abcde");
//        hashSet.add("raysonxin");
//        Iterator<String> iterable = hashSet.iterator();
//        while(iterable.hasNext()){
//            String value=iterable.next();
//            System.out.println(value);
//        }
//
//        System.out.println(hashSet.size());
//
//
//        LinkedHashSet<String> linkedHashSet=new LinkedHashSet<String>();

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("a", "1");

    }
//
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
//                   boolean evict) {
//        HashMap.Node<K,V>[] tab; HashMap.Node<K,V> p; int n, i;
//        // 如果数组为空，首先进行初始化
//        if ((tab = table) == null || (n = tab.length) == 0)
//            n = (tab = resize()).length;
//        // 当前位置为空，则直接赋值
//        if ((p = tab[i = (n - 1) & hash]) == null)
//            tab[i] = newNode(hash, key, value, null);
//        else {//出现冲突
//            HashMap.Node<K,V> e; K k;
//            // 若冲突位置hash相同、key相同则直接覆盖
//            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
//                e = p;
//            // 若冲突位置为树节点，则加入到树中
//            else if (p instanceof HashMap.TreeNode)
//                e = ((HashMap.TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
//            else {
//                //此时说明为链表，会从头开始遍历，添加到链表尾部。若链表长度达到8，则树化。
//                for (int binCount = 0; ; ++binCount) {
//                    if ((e = p.next) == null) {
//                        //添加到链表尾部
//                        p.next = newNode(hash, key, value, null);
//                        //长度为8，执行树化。
//                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
//                            treeifyBin(tab, hash);
//                        break;
//                    }
//                    //这里说明链表中节点与待加入节点hash一致、key一致
//                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
//                        break;
//                    p = e;
//                }
//            }
//            //这里处理hash一致、key一致，覆盖
//            if (e != null) { // existing mapping for key
//                V oldValue = e.value;
//                if (!onlyIfAbsent || oldValue == null)
//                    e.value = value;
//                afterNodeAccess(e);
//                return oldValue;
//            }
//        }
//        //fail-fast
//        ++modCount;
//        //若数组元素达到阈值，则执行扩容
//        if (++size > threshold)
//            resize();
//        afterNodeInsertion(evict);
//        return null;
//    }
//
//    final HashMap.Node<K,V> getNode(int hash, Object key) {
//        HashMap.Node<K,V>[] tab; HashMap.Node<K,V> first, e; int n; K k;
//        //tab不空，hash桶不空
//        if ((tab = table) != null && (n = tab.length) > 0 && (first = tab[(n - 1) & hash]) != null) {
//            // 检查第一个节点是否符合要求：hash相等，key相等（==或equals）
//            if (first.hash == hash && // always check first node
//                    ((k = first.key) == key || (key != null && key.equals(k))))
//                return first;
//            if ((e = first.next) != null) {
//                // 如果是树，则在树中找
//                if (first instanceof HashMap.TreeNode)
//                    return ((HashMap.TreeNode<K,V>)first).getTreeNode(hash, key);
//                //这里是链表查找
//                do {
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k))))
//                        return e;
//                } while ((e = e.next) != null);
//            }
//        }
//        return null;
//    }
}

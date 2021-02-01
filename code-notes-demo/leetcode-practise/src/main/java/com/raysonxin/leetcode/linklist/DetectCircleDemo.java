package com.raysonxin.leetcode.linklist;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author raysonxin
 * @since 2021/1/30
 */
public class DetectCircleDemo {

    public static void main(String[] args) {
//        int[] texts = {1, 2, 3, 4, 5, 6, 3};
//        Node head = buildCircleLinklist(texts);
//        Node node = detectCircleByFastSlow(head);
//        printResult(node);
//        node = detectCircleByExtraSpace(head);
//        printResult(node);
//
//        head = buildLinklist(texts);
//        node = detectCircleByFastSlow(head);
//        printResult(node);
//        node = detectCircleByExtraSpace(head);
//        printResult(node);

        int[] texts = {1, 2, 3, 4, 5, 6, 3};
        Node head = buildCircleLinklist(texts);
        Node node = detectCircleByFastSlow(head);
        printResult(node);

        head = buildLinklist(texts);
        node = detectCircleByFastSlow(head);
        printResult(node);
    }

    private static void printResult(Node node) {
        if (node == null) {
            System.out.println("无环");
        } else {
            System.out.println("有环，入口为：" + node.data);
        }
    }

    /**
     * 检测链表是否有环，若有环则找到入口
     * 使用快慢指针的方式
     *
     * @param head 链表头节点
     * @return 若为空，则不存在环；不为空，则输出为入口节点
     */
    private static Node detectCircleByFastSlow(Node head) {
        // 快慢指针从头节点开始
        Node fast = head;
        Node slow = head;
        // 用于记录相遇点
        Node encounter = null;
        // fast一次走两步，所以要保证next和next.next都不为空，为空就说明无环
        while (fast.next != null && fast.next.next != null) {
            // fast走两步，slow走一步
            fast = fast.next.next;
            slow = slow.next;
            // fast和slow一样，说明相遇了，或者fast追上slow了。
            if (fast == slow) {
                // 记录相遇点，fast或slow都一样
                encounter = fast;
                // 相遇了，就退出环检测过程
                break;
            }
        }

        // 如果encounter为空，说明没有环，就不用继续找环入口了。
        if (encounter == null) {
            return encounter;
        }

        // fast回到head位置
        fast = head;
        // 只要两者不相遇，就一直走下去
        while (fast != slow) {
            // fast每次走一步，slow每次走一步，速度一样
            fast = fast.next;
            slow = slow.next;
        }

        // 相遇点，即为环入口
        return fast;
    }

    /**
     * 检查链表中是否有环：采用node唯一信息，配合HashMap
     *
     * @param head 头节点
     * @return 若为空，则不存在环；不为空，则输出为入口节点
     */
    private static Node detectCircleByExtraSpace(Node head) {
        // 用这个map存储所有遍历过的节点
        Map<Integer, Node> nodeHashCodeMap = new HashMap<>();
        Node node = head.next;
        // 节点不空则继续遍历，循环停止的条件有两个：一是遍历到链表尾节点，二是发现重复元素。
        while (node != null) {
            // 获取节点hashCode
            Integer hashCode = node.hashCode();
            // 判断是否曾经遍历过
            if (nodeHashCodeMap.containsKey(hashCode)) {
                // 如果曾经遍历过，说明有环，并且这是入口
                return node;
            }
            // 遍历过即加入map
            nodeHashCodeMap.put(node.hashCode(), node);
            // 下一个
            node = node.next;
        }
        // 走到这里，就说明没有环了。
        return null;
    }

    /**
     * 构建链表（无环）
     */
    private static Node buildLinklist(int[] texts) {
        Node head = new Node(0);
        Node node = head;
        for (int text : texts) {
            Node next = new Node(text);
            node.next = next;
            node = next;
        }
        return head;
    }

    /**
     * 构建链表，可能有环
     */
    private static Node buildCircleLinklist(int[] texts) {
        Node head = new Node(0);
        Node node = head;
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int text : texts) {
            Node next = null;
            if (nodeMap.containsKey(text)) {
                next = nodeMap.get(text);
            } else {
                next = new Node(text);
                nodeMap.put(text, next);
            }

            node.next = next;
            node = next;
        }
        return head;
    }
}

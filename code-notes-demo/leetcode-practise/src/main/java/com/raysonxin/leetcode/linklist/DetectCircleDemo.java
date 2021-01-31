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
        int[] texts = {1, 2, 3, 4, 5, 6, 3};
        Node head = buildCircleLinklist(texts);
        Node node = detectCircle(head);
        printResult(node);
        node = detectCircle2(head);
        printResult(node);

        head = buildLinklist(texts);
        node = detectCircle(head);
        printResult(node);
        node = detectCircle2(head);
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
    private static Node detectCircle(Node head) {
        Node fast = head;
        Node slow = head;
        Node encounter = null;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                encounter = fast;
                break;
            }
        }

        if (encounter == null) {
            return encounter;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

    /**
     * 检查链表中是否有环：采用node唯一信息，配合HashMap
     *
     * @param head 头节点
     * @return 若为空，则不存在环；不为空，则输出为入口节点
     */
    private static Node detectCircle2(Node head) {
        Map<NodeIdentifier, Node> nodeHashCodeMap = new HashMap<>();
        Node node = head.next;
        while (node != null) {
            NodeIdentifier nodeIdentifier = new NodeIdentifier(node.hashCode(), node);
            if (nodeHashCodeMap.containsKey(nodeIdentifier)) {
                return node;
            }
            nodeHashCodeMap.put(nodeIdentifier, node);
            node = node.next;
        }
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

    /**
     * 链表唯一识别信息
     * */
    public static class NodeIdentifier {
        public NodeIdentifier(int hashCode, Node node) {
            this.hashCode = hashCode;
            this.node = node;
        }

        public int hashCode;
        public Node node;

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            if (obj instanceof NodeIdentifier) {
                NodeIdentifier other = (NodeIdentifier) obj;
                return this.hashCode == other.hashCode;
            }

            return false;
        }

        @Override
        public int hashCode() {
            return this.hashCode;
        }
    }
}

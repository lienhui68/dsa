package com.eh.ftd.dsa.ds;

import java.util.Scanner;

/**
 * 环形队列
 *
 * @author David Li
 * @create 2020/06/11 18:26
 */
public class CircleQueue<T> {
    /**
     * 容量
     */
    private int capacity;
    /**
     * 计数器
     */
    private int count;
    /**
     * 头部 指向头部前一个
     */
    private int head;
    /**
     * 尾部
     */
    private int rear;

    /**
     * 数组容器
     */
    private Object[] data;

    public CircleQueue() {
    }

    public CircleQueue(int capacity) {
        this.capacity = capacity;
        data = new Object[capacity];
        head = -1;
        rear = -1;
    }

    /**
     * 增加一个元素
     *
     * @param t
     */
    public void add(T t) {
        // 查看是否溢出
        if (count == capacity) {
            throw new RuntimeException("溢出");
        }
        // rear如果超出数组尾部则应该重置成数组头
        rear++;
        if (rear == capacity) {
            rear = 0;
        }
        data[rear] = t;
        count++;
    }

    /**
     * 删除一个元素
     *
     * @return
     */
    public T remove() {
        if (count == 0) {
            return null;
        }
        int front = head;
        head++;
        if (head == capacity - 1) {
            head = 0;
        }
        count--;
        return (T) data[++front];
    }

    public T peek() {
        if (count == 0) {
            return null;
        }
        int front = head + 1;
        return (T) data[front];
    }

    /**
     * 展示所有元素, 从队首到队尾
     */
    public void display() {
        if (count < 1) {
            return;
        }
        if (head < rear) {
            for (int i = head + 1; i <= rear; i++) {
                System.out.printf(data[i] + " ");
            }
        } else {
            for (int i = head + 1; i < capacity; i++) {
                System.out.printf(data[i] + " ");
            }
            for (int i = 0; i <= rear; i++) {
                System.out.printf(data[i] + " ");
            }
        }
        System.out.println();
    }

    /**
     * 演示程序，通过输入输出验证队列正确性
     *
     * @param args
     */
    public static void main(String[] args) {
        CircleQueue<Integer> queue = new CircleQueue<>(5);


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("a 增加");
            System.out.println("r 删除");
            System.out.println("p 查看队首元素");
            System.out.println("d 查看全部元素");
            char c = scanner.next().charAt(0);
            switch (c) {
                case 'a':
                    int x = scanner.nextInt();
                    queue.add(x);
                    break;
                case 'r':
                    queue.remove();
                    break;
                case 'p':
                    System.out.println(queue.peek());
                    break;
                case 'd':
                    queue.display();
                    break;
            }
        }
    }


}

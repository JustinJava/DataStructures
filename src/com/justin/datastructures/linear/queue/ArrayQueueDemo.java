package com.justin.datastructures.linear.queue;

import java.util.Scanner;

/**
 * @program: DataStructures
 * @description: 使用数组实现的队列
 * 一、队列特点：
 * 1、队列是一个有序列表，可以用数组或链表来实现
 * 2、队列遵循先入先出原则
 * 3、队列在尾部加数据，从头部取数据
 * @author: JustinQin
 * @create: 2021/7/4 18:13
 * @version: v1.0.0
 **/
public class ArrayQueueDemo {
    public static void main(String[] args) {

        //
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接口用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列中取数据");
            System.out.println("h(head)：查看队列头的数据");

            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQuque();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }

    //使用数组模拟队列编写一个ArrayQueue类
    static class ArrayQueue {
        private int maxSize; //表示数组的最大容量
        private int front; //队列头
        private int rear; //队列尾
        private int[] arr; //该数据用于存放数据，模拟队列

        //创建队列的构造器
        public ArrayQueue(int arryMaxSize) {
            maxSize = arryMaxSize;
            arr = new int[arryMaxSize];
            front = -1; //指向队列头部，分析出front是指向队列头的前一个位置
            rear = -1; //指向队列尾部，指向队列的数据（即就是队列的最后一个数据）
        }

        //判断队列是否满
        public boolean isFull() {
            return rear == maxSize - 1;
        }

        //判断队列是否为空
        public boolean isEmpty() {
            return rear == front;
        }

        //添加数据到队列
        public void addQueue(int n) {
            //判断队列是否满
            if (isFull()) {
                System.out.println("队列满，不能加入数据");
                return;
            }
            rear++; //让rear后移
            arr[rear] = n;
        }

        //获取队列的数据，出队列
        public int getQuque() {
            if (isEmpty()) {
                throw new RuntimeException("队列空，不能取数据");
            }
            front++;
            return arr[front];
        }

        //显示队列的所有数据
        public void showQueue() {
            if (isEmpty()) {
                System.out.println("队列空的，没有数据~");
                return;
            }
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr[%d]=%d\n", i, arr[i]);
            }
        }

        //显示队列的头数据，注意不是取出数据
        public int headQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空，没有数据~");
            }
            return arr[front + 1];
        }

    }

}

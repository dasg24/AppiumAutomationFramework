package com.das.datastructure;

public class CircularQueue {

	public static String[] arr;
	public static int topOfQueue;
	public static int beginningOfQueue;
	public static int size;

	public CircularQueue(int size) {
		CircularQueue.arr = new String[size];
		CircularQueue.size = size;
		CircularQueue.topOfQueue = -1;
		CircularQueue.beginningOfQueue = -1;
		System.out.println("The Circular Queue is successfully created with size of " + size);
	}

	// isEmpty
	public static boolean isEmpty() {
		if (topOfQueue == -1) {
			return true;
		} else {
			return false;
		}
	}

	// isFull
	public static boolean isFull() {
		if (topOfQueue + 1 == beginningOfQueue) {
			return true;
		} else if (beginningOfQueue == 0 && topOfQueue + 1 == size) {
			return true;
		} else {
			return false;
		}
	}

	// enQueue
	public static synchronized void enQueue(String value) {
		if (isFull()) {
			System.out.println("The Circular Queue is full!");
		} else if (isEmpty()) {
			beginningOfQueue = 0;
			topOfQueue++;
			arr[topOfQueue] = value;
			System.out.println("Successfully inserted " + value + " in the queue");
		} else {
			if (topOfQueue + 1 == size) {
				topOfQueue = 0;
			} else {
				topOfQueue++;
			}
			arr[topOfQueue] = value;
			System.out.println("Successfully inserted " + value + " in the queue");
		}
	}

	// deQueue
	public static synchronized String deQueue() {
		if (isEmpty()) {
			System.out.println("The Circular Queue is empty!");
			return "Empty Queue";
		} else {
			String result = arr[beginningOfQueue];
			arr[beginningOfQueue] = "";
			if (beginningOfQueue == topOfQueue) {
				beginningOfQueue = topOfQueue = -1;
			} else if (beginningOfQueue + 1 == size) {
				beginningOfQueue = 0;
			} else {
				beginningOfQueue++;
			}
			return result;
		}
	}

	// peek
	public static String peek() {
		if (isEmpty()) {
			System.out.println("The Circular Queue is emtpy");
			return "Empty Queue";
		} else {
			return arr[beginningOfQueue];
		}
	}

	// deleteQueue
	public static void deleteQueue() {
		arr = null;
		System.out.println("The Circular Queue is successfully deleted!");
	}

}

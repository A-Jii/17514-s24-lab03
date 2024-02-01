package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: 
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        // mQueue = new LinkedIntQueue();
        mQueue = new ArrayIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
    }

    @Test
    public void testIsEmpty() {
        // This is an example unit test
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        // TODO: write your own unit test
        mQueue.enqueue(1);
        assertFalse(mQueue.isEmpty());
        // fail("Test not implemented");
    }

    @Test
    public void testPeekEmptyQueue() {
        // TODO: write your own unit test
        assertNull(mQueue.peek());
        // fail("Test not implemented");
    }

    @Test
    public void testPeekNoEmptyQueue() {
        // TODO: write your own unit test
        mQueue.enqueue(1);
        assertEquals(Integer.valueOf(1), mQueue.peek());
        // fail("Test not implemented");
    }

    @Test
    public void testEnqueue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }

    @Test
    public void testDequeue() {
        // TODO: write your own unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
        }

        for (int i = 0; i < testList.size(); i++) {
            assertEquals(testList.get(i), mQueue.dequeue());
            assertEquals(testList.size() - i - 1, mQueue.size());
        }

        assertTrue(mQueue.isEmpty());
        // fail("Test not implemented");
    }

    @Test
    public void testContent() throws IOException {
        // This is an example unit test
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }

    @Test
    public void testEnqueueBeyondInitialCapacity() {
        // Assume the initial capacity of the queue is 10
        int initialCapacity = 10;
    
        // Enqueue more elements than the initial capacity
        for (int i = 0; i < initialCapacity + 5; i++) {
            mQueue.enqueue(i);
        }
        
        int status = 0;

        // Check that all elements were added successfully
        for (int i = 0; i < initialCapacity + 5; i++) {
            if (i != mQueue.dequeue()) {
                status = 1;
            }
        }

        assertEquals(0, status);
    }

    @Test
    public void testDequeueEmptyQueue() {
        // Dequeue from an empty queue
        if (mQueue.isEmpty()) {
            assertNull(mQueue.dequeue());
        }
    }

    @Test
    public void testSizeDontChange() {
        // Enqueue and dequeue elements from the queue
        for (int i = 0; i < 10; i++) {
            mQueue.enqueue(i);
        }
        for (int i = 0; i < 10; i++) {
            mQueue.dequeue();
        }

        // Check that the size of the queue is 0
        assertEquals(0, mQueue.size());
    }

    @Test
    public void peekEmptyQueue() {
        // Peek from an empty queue
        if (mQueue.isEmpty()) {
            assertNull(mQueue.peek());
        }
    }

    @Test
    public void testEnsureCapacity() {
        for (int i = 0; i < 5; i++) {
            mQueue.enqueue(i);
        }
        mQueue.dequeue();
        for (int i = 5; i < 13; i++) {
            mQueue.enqueue(i);
        }

        int status = 0;
        for (int i = 1; i <= 12; i++) {
            if (i != mQueue.dequeue()) {
                status = 1;
            }
        }

        assertEquals(0, status);
    }

    @Test
    public void testClear() {
        // Enqueue some elements
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.enqueue(3);

        // Clear the queue
        mQueue.clear();

        // Check that the queue is empty
        assertTrue(mQueue.isEmpty());
        assertEquals(0, mQueue.size());
        assertNull(mQueue.peek());
    }


}

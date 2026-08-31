---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1114.Print%20in%20Order/README_EN.md
tags:
    - Concurrency
---

<!-- problem:start -->

# [1114. Print in Order](https://leetcode.com/problems/print-in-order)

[中文文档](/solution/1100-1199/1114.Print%20in%20Order/README.md)

## Description

<!-- description:start -->

<p>Suppose we have a class:</p>

<pre>
public class Foo {
  public void first() { print(&quot;first&quot;); }
  public void second() { print(&quot;second&quot;); }
  public void third() { print(&quot;third&quot;); }
}
</pre>

<p>The same instance of <code>Foo</code> will be passed to three different threads. Thread A will call <code>first()</code>, thread B will call <code>second()</code>, and thread C will call <code>third()</code>. Design a mechanism and modify the program to ensure that <code>second()</code> is executed after <code>first()</code>, and <code>third()</code> is executed after <code>second()</code>.</p>

<p><strong>Note:</strong></p>

<p>We do not know how the threads will be scheduled in the operating system, even though the numbers in the input seem to imply the ordering. The input format you see is mainly to ensure our tests&#39; comprehensiveness.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> &quot;firstsecondthird&quot;
<strong>Explanation:</strong> There are three threads being fired asynchronously. The input [1,2,3] means thread A calls first(), thread B calls second(), and thread C calls third(). &quot;firstsecondthird&quot; is the correct output.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,2]
<strong>Output:</strong> &quot;firstsecondthird&quot;
<strong>Explanation:</strong> The input [1,3,2] means thread A calls first(), thread B calls third(), and thread C calls second(). &quot;firstsecondthird&quot; is the correct output.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums</code> is a permutation of <code>[1, 2, 3]</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Multithreading + Lock or Semaphore

We can use three semaphores $a$, $b$, and $c$ to control the execution order of the three threads. Initially, the count of semaphore $a$ is $1$, and the counts of $b$ and $c$ are $0$.

When thread $A$ executes the `first()` method, it first needs to acquire semaphore $a$. After acquiring successfully, it executes the `first()` method, and then releases semaphore $b$. This allows thread $B$ to acquire semaphore $b$ and execute the `second()` method.

When thread $B$ executes the `second()` method, it first needs to acquire semaphore $b$. After acquiring successfully, it executes the `second()` method, and then releases semaphore $c$. This allows thread $C$ to acquire semaphore $c$ and execute the `third()` method.

When thread $C$ executes the `third()` method, it first needs to acquire semaphore $c$. After acquiring successfully, it executes the `third()` method, and then releases semaphore $a$. This allows thread $A$ to acquire semaphore $a$ and execute the `first()` method.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Foo:
    def __init__(self):
        self.l2 = threading.Lock()
        self.l3 = threading.Lock()
        self.l2.acquire()
        self.l3.acquire()

    def first(self, printFirst: 'Callable[[], None]') -> None:
        printFirst()
        self.l2.release()

    def second(self, printSecond: 'Callable[[], None]') -> None:
        self.l2.acquire()
        printSecond()
        self.l3.release()

    def third(self, printThird: 'Callable[[], None]') -> None:
        self.l3.acquire()
        printThird()
```

#### Java

```java
class Foo {
    private Semaphore a = new Semaphore(1);
    private Semaphore b = new Semaphore(0);
    private Semaphore c = new Semaphore(0);

    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        a.acquire(1);
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        b.release(1);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        b.acquire(1);
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        c.release(1);
    }

    public void third(Runnable printThird) throws InterruptedException {
        c.acquire(1);
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        a.release(1);
    }
}
```

#### C++

```cpp
class Foo {
private:
    mutex m2, m3;

public:
    Foo() {
        m2.lock();
        m3.lock();
    }

    void first(function<void()> printFirst) {
        printFirst();
        m2.unlock();
    }

    void second(function<void()> printSecond) {
        m2.lock();
        printSecond();
        m3.unlock();
    }

    void third(function<void()> printThird) {
        m3.lock();
        printThird();
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Python3

```python
from threading import Semaphore


class Foo:
    def __init__(self):
        self.a = Semaphore(1)
        self.b = Semaphore(0)
        self.c = Semaphore(0)

    def first(self, printFirst: 'Callable[[], None]') -> None:
        self.a.acquire()
        # printFirst() outputs "first". Do not change or remove this line.
        printFirst()
        self.b.release()

    def second(self, printSecond: 'Callable[[], None]') -> None:
        self.b.acquire()
        # printSecond() outputs "second". Do not change or remove this line.
        printSecond()
        self.c.release()

    def third(self, printThird: 'Callable[[], None]') -> None:
        self.c.acquire()
        # printThird() outputs "third". Do not change or remove this line.
        printThird()
        self.a.release()
```

#### C++

```cpp
#include <semaphore.h>

class Foo {
private:
    sem_t a, b, c;

public:
    Foo() {
        sem_init(&a, 0, 1);
        sem_init(&b, 0, 0);
        sem_init(&c, 0, 0);
    }

    void first(function<void()> printFirst) {
        sem_wait(&a);
        // printFirst() outputs "first". Do not change or remove this line.
        printFirst();
        sem_post(&b);
    }

    void second(function<void()> printSecond) {
        sem_wait(&b);
        // printSecond() outputs "second". Do not change or remove this line.
        printSecond();
        sem_post(&c);
    }

    void third(function<void()> printThird) {
        sem_wait(&c);
        // printThird() outputs "third". Do not change or remove this line.
        printThird();
        sem_post(&a);
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

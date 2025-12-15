---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1116.Print%20Zero%20Even%20Odd/README_EN.md
tags:
    - Concurrency
---

<!-- problem:start -->

# [1116. Print Zero Even Odd](https://leetcode.com/problems/print-zero-even-odd)

[中文文档](/solution/1100-1199/1116.Print%20Zero%20Even%20Odd/README.md)

## Description

<!-- description:start -->

<p>You have a function <code>printNumber</code> that can be called with an integer parameter and prints it to the console.</p>

<ul>
	<li>For example, calling <code>printNumber(7)</code> prints <code>7</code> to the console.</li>
</ul>

<p>You are given an instance of the class <code>ZeroEvenOdd</code> that has three functions: <code>zero</code>, <code>even</code>, and <code>odd</code>. The same instance of <code>ZeroEvenOdd</code> will be passed to three different threads:</p>

<ul>
	<li><strong>Thread A:</strong> calls <code>zero()</code> that should only output <code>0</code>&#39;s.</li>
	<li><strong>Thread B:</strong> calls <code>even()</code> that should only output even numbers.</li>
	<li><strong>Thread C:</strong> calls <code>odd()</code> that should only output odd numbers.</li>
</ul>

<p>Modify the given class to output the series <code>&quot;010203040506...&quot;</code> where the length of the series must be <code>2n</code>.</p>

<p>Implement the <code>ZeroEvenOdd</code> class:</p>

<ul>
	<li><code>ZeroEvenOdd(int n)</code> Initializes the object with the number <code>n</code> that represents the numbers that should be printed.</li>
	<li><code>void zero(printNumber)</code> Calls <code>printNumber</code> to output one zero.</li>
	<li><code>void even(printNumber)</code> Calls <code>printNumber</code> to output one even number.</li>
	<li><code>void odd(printNumber)</code> Calls <code>printNumber</code> to output one odd number.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> &quot;0102&quot;
<strong>Explanation:</strong> There are three threads being fired asynchronously.
One of them calls zero(), the other calls even(), and the last one calls odd().
&quot;0102&quot; is the correct output.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> &quot;0102030405&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Multithreading + Semaphore

We use three semaphores $z$, $e$, and $o$ to control the execution order of the three threads, where $z$ is initially set to $1$, and $e$ and $o$ are set to $0$.

- Semaphore $z$ controls the execution of the `zero` function. When the value of semaphore $z$ is $1$, the `zero` function can be executed. After execution, the value of semaphore $z$ is set to $0$, and the value of semaphore $e$ or $o$ is set to $1$, depending on whether the `even` function or the `odd` function needs to be executed next.
- Semaphore $e$ controls the execution of the `even` function. When the value of semaphore $e$ is $1$, the `even` function can be executed. After execution, the value of semaphore $z$ is set to $1$, and the value of semaphore $e$ is set to $0$.
- Semaphore $o$ controls the execution of the `odd` function. When the value of semaphore $o$ is $1$, the `odd` function can be executed. After execution, the value of semaphore $z$ is set to $1$, and the value of semaphore $o$ is set to $0$.

The time complexity is $O(n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
from threading import Semaphore


class ZeroEvenOdd:
    def __init__(self, n):
        self.n = n
        self.z = Semaphore(1)
        self.e = Semaphore(0)
        self.o = Semaphore(0)

    # printNumber(x) outputs "x", where x is an integer.
    def zero(self, printNumber: 'Callable[[int], None]') -> None:
        for i in range(self.n):
            self.z.acquire()
            printNumber(0)
            if i % 2 == 0:
                self.o.release()
            else:
                self.e.release()

    def even(self, printNumber: 'Callable[[int], None]') -> None:
        for i in range(2, self.n + 1, 2):
            self.e.acquire()
            printNumber(i)
            self.z.release()

    def odd(self, printNumber: 'Callable[[int], None]') -> None:
        for i in range(1, self.n + 1, 2):
            self.o.acquire()
            printNumber(i)
            self.z.release()
```

#### Java

```java
class ZeroEvenOdd {
    private int n;
    private Semaphore z = new Semaphore(1);
    private Semaphore e = new Semaphore(0);
    private Semaphore o = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; ++i) {
            z.acquire(1);
            printNumber.accept(0);
            if (i % 2 == 0) {
                o.release(1);
            } else {
                e.release(1);
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            e.acquire(1);
            printNumber.accept(i);
            z.release(1);
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            o.acquire(1);
            printNumber.accept(i);
            z.release(1);
        }
    }
}
```

#### C++

```cpp
#include <semaphore.h>

class ZeroEvenOdd {
private:
    int n;
    sem_t z, e, o;

public:
    ZeroEvenOdd(int n) {
        this->n = n;
        sem_init(&z, 0, 1);
        sem_init(&e, 0, 0);
        sem_init(&o, 0, 0);
    }

    // printNumber(x) outputs "x", where x is an integer.
    void zero(function<void(int)> printNumber) {
        for (int i = 0; i < n; ++i) {
            sem_wait(&z);
            printNumber(0);
            if (i % 2 == 0) {
                sem_post(&o);
            } else {
                sem_post(&e);
            }
        }
    }

    void even(function<void(int)> printNumber) {
        for (int i = 2; i <= n; i += 2) {
            sem_wait(&e);
            printNumber(i);
            sem_post(&z);
        }
    }

    void odd(function<void(int)> printNumber) {
        for (int i = 1; i <= n; i += 2) {
            sem_wait(&o);
            printNumber(i);
            sem_post(&z);
        }
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

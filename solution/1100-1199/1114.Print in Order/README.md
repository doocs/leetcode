---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1114.Print%20in%20Order/README.md
tags:
    - 多线程
---

<!-- problem:start -->

# [1114. 按序打印](https://leetcode.cn/problems/print-in-order)

[English Version](/solution/1100-1199/1114.Print%20in%20Order/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个类：</p>

<pre>
public class Foo {
&nbsp; public void first() { print("first"); }
&nbsp; public void second() { print("second"); }
&nbsp; public void third() { print("third"); }
}</pre>

<p>三个不同的线程 A、B、C 将会共用一个&nbsp;<code>Foo</code>&nbsp;实例。</p>

<ul>
	<li>线程 A 将会调用 <code>first()</code> 方法</li>
	<li>线程 B 将会调用&nbsp;<code>second()</code> 方法</li>
	<li>线程 C 将会调用 <code>third()</code> 方法</li>
</ul>

<p>请设计修改程序，以确保 <code>second()</code> 方法在 <code>first()</code> 方法之后被执行，<code>third()</code> 方法在 <code>second()</code> 方法之后被执行。</p>

<p><strong>提示：</strong></p>

<ul>
	<li>尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。</li>
	<li>你看到的输入格式主要是为了确保测试的全面性。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>"firstsecondthird"
<strong>解释：</strong>
有三个线程会被异步启动。输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。正确的输出是 "firstsecondthird"。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,2]
<strong>输出：</strong>"firstsecondthird"
<strong>解释：</strong>
输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。正确的输出是 "firstsecondthird"。</pre>

<p>&nbsp;</p>

<ul>
</ul>
<strong>提示：</strong>

<ul>
	<li><code>nums</code> 是 <code>[1, 2, 3]</code> 的一组排列</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：多线程 + 锁或信号量

我们可以用三个信号量 $a$, $b$, $c$ 来控制三个线程的执行顺序，初始时 $a$ 信号量的计数为 $1$，$b$ 和 $c$ 的计数为 $0$。

线程 $A$ 在执行 `first()` 方法时，首先需要获取 $a$ 信号量，获取成功后执行 `first()` 方法，然后释放 $b$ 信号量，这样线程 $B$ 就可以获取 $b$ 信号量并执行 `second()` 方法。

线程 $B$ 在执行 `second()` 方法时，首先需要获取 $b$ 信号量，获取成功后执行 `second()` 方法，然后释放 $c$ 信号量，这样线程 $C$ 就可以获取 $c$ 信号量并执行 `third()` 方法。

线程 $C$ 在执行 `third()` 方法时，首先需要获取 $c$ 信号量，获取成功后执行 `third()` 方法，然后释放 $a$ 信号量，这样线程 $A$ 就可以获取 $a$ 信号量并执行 `first()` 方法。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

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

### 方法二

<!-- tabs:start -->

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

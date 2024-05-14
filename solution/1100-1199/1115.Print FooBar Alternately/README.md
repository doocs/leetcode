---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1115.Print%20FooBar%20Alternately/README.md
tags:
    - 多线程
---

# [1115. 交替打印 FooBar](https://leetcode.cn/problems/print-foobar-alternately)

[English Version](/solution/1100-1199/1115.Print%20FooBar%20Alternately/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个类：</p>

<pre>
class FooBar {
  public void foo() {
&nbsp; &nbsp; for (int i = 0; i &lt; n; i++) {
&nbsp; &nbsp; &nbsp; print("foo");
&nbsp;   }
  }

  public void bar() {
&nbsp; &nbsp; for (int i = 0; i &lt; n; i++) {
&nbsp; &nbsp; &nbsp; print("bar");
&nbsp; &nbsp; }
  }
}
</pre>

<p>两个不同的线程将会共用一个 <code>FooBar</code>&nbsp;实例：</p>

<ul>
	<li>线程 A 将会调用&nbsp;<code>foo()</code>&nbsp;方法，而</li>
	<li>线程 B 将会调用&nbsp;<code>bar()</code>&nbsp;方法</li>
</ul>

<p>请设计修改程序，以确保 <code>"foobar"</code> 被输出 <code>n</code> 次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>"foobar"
<strong>解释：</strong>这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>"foobarfoobar"
<strong>解释：</strong>"foobar" 将被输出两次。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## 解法

### 方法一：多线程 + 信号量

我们用两个信号量 $f$ 和 $b$ 来控制两个线程的执行顺序，其中 $f$ 初始值为 $1$，而 $b$ 初始值为 $0$，表示线程 $A$ 先执行。

当线程 $A$ 执行时，首先会执行 $f$ 的 $acquire$ 操作，此时 $f$ 的值变为 $0$，线程 $A$ 获得了 $f$ 的使用权，可以执行 $foo$ 函数，然后执行 $b$ 的 $release$ 操作，此时 $b$ 的值变为 $1$，线程 $B$ 获得了 $b$ 的使用权，可以执行 $bar$ 函数。

当线程 $B$ 执行时，首先会执行 $b$ 的 $acquire$ 操作，此时 $b$ 的值变为 $0$，线程 $B$ 获得了 $b$ 的使用权，可以执行 $bar$ 函数，然后执行 $f$ 的 $release$ 操作，此时 $f$ 的值变为 $1$，线程 $A$ 获得了 $f$ 的使用权，可以执行 $foo$ 函数。

因此，我们只需要循环 $n$ 次，每次执行 $foo$ 和 $bar$ 函数时，先执行 $acquire$ 操作，再执行 $release$ 操作即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
from threading import Semaphore


class FooBar:
    def __init__(self, n):
        self.n = n
        self.f = Semaphore(1)
        self.b = Semaphore(0)

    def foo(self, printFoo: "Callable[[], None]") -> None:
        for _ in range(self.n):
            self.f.acquire()
            # printFoo() outputs "foo". Do not change or remove this line.
            printFoo()
            self.b.release()

    def bar(self, printBar: "Callable[[], None]") -> None:
        for _ in range(self.n):
            self.b.acquire()
            # printBar() outputs "bar". Do not change or remove this line.
            printBar()
            self.f.release()
```

```java
class FooBar {
    private int n;
    private Semaphore f = new Semaphore(1);
    private Semaphore b = new Semaphore(0);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            f.acquire(1);
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            b.release(1);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            b.acquire(1);
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            f.release(1);
        }
    }
}
```

```cpp
#include <semaphore.h>

class FooBar {
private:
    int n;
    sem_t f, b;

public:
    FooBar(int n) {
        this->n = n;
        sem_init(&f, 0, 1);
        sem_init(&b, 0, 0);
    }

    void foo(function<void()> printFoo) {
        for (int i = 0; i < n; i++) {
            sem_wait(&f);
            // printFoo() outputs "foo". Do not change or remove this line.
            printFoo();
            sem_post(&b);
        }
    }

    void bar(function<void()> printBar) {
        for (int i = 0; i < n; i++) {
            sem_wait(&b);
            // printBar() outputs "bar". Do not change or remove this line.
            printBar();
            sem_post(&f);
        }
    }
};
```

<!-- tabs:end -->

<!-- end -->

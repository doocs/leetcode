# [1115. Print FooBar Alternately](https://leetcode.com/problems/print-foobar-alternately)

[中文文档](/solution/1100-1199/1115.Print%20FooBar%20Alternately/README.md)

## Description

<p>Suppose you are given the following code:</p>

<pre>
class FooBar {
  public void foo() {
    for (int i = 0; i &lt; n; i++) {
      print(&quot;foo&quot;);
    }
  }

  public void bar() {
    for (int i = 0; i &lt; n; i++) {
      print(&quot;bar&quot;);
    }
  }
}
</pre>

<p>The same instance of <code>FooBar</code> will be passed to two different threads:</p>

<ul>
	<li>thread <code>A</code> will call <code>foo()</code>, while</li>
	<li>thread <code>B</code> will call <code>bar()</code>.</li>
</ul>

<p>Modify the given program to output <code>&quot;foobar&quot;</code> <code>n</code> times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> &quot;foobar&quot;
<strong>Explanation:</strong> There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar().
&quot;foobar&quot; is being output 1 time.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> &quot;foobarfoobar&quot;
<strong>Explanation:</strong> &quot;foobar&quot; is being output 2 times.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **...**

```

```

<!-- tabs:end -->

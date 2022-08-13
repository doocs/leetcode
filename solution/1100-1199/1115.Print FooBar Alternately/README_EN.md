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
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> &quot;foobar&quot;
<strong>Explanation:</strong> There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar().
&quot;foobar&quot; is being output 1 time.
</pre>

<p><strong>Example 2:</strong></p>

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
class FooBar:
    def __init__(self, n):
        self.n = n
        self.fooLock = threading.Lock()
        self.barLock = threading.Lock()
        self.barLock.acquire()

    def foo(self, printFoo: 'Callable[[], None]') -> None:
        for i in range(self.n):
            self.fooLock.acquire()
            printFoo()
            self.barLock.release()

    def bar(self, printBar: 'Callable[[], None]') -> None:
        for i in range(self.n):
            self.barLock.acquire()
            printBar()
            self.fooLock.release()
```

### **Java**

```java
class FooBar {
    private int n;
    private final Semaphore fooSem = new Semaphore(1);
    private final Semaphore barSem = new Semaphore(0);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            fooSem.acquire();
            printFoo.run();
            barSem.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            barSem.acquire();
            printBar.run();
            fooSem.release();
        }
    }
}
```

### **C++**

```cpp
class FooBar {
private:
    int n;
    mutex fooMu, barMu;

public:
    FooBar(int n) {
        this->n = n;
        barMu.lock();
    }

    void foo(function<void()> printFoo) {
        for (int i = 0; i < n; i++) {
            fooMu.lock();
            printFoo();
            barMu.unlock();
        }
    }

    void bar(function<void()> printBar) {
        for (int i = 0; i < n; i++) {
            barMu.lock();
            printBar();
            fooMu.unlock();
        }
    }
};
```

### **...**

```

```

<!-- tabs:end -->

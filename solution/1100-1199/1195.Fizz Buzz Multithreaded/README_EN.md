# [1195. Fizz Buzz Multithreaded](https://leetcode.com/problems/fizz-buzz-multithreaded)

[中文文档](/solution/1100-1199/1195.Fizz%20Buzz%20Multithreaded/README.md)

<!-- tags:Concurrency -->

## Description

<p>You have the four functions:</p>

<ul>
	<li><code>printFizz</code> that prints the word <code>&quot;fizz&quot;</code> to the console,</li>
	<li><code>printBuzz</code> that prints the word <code>&quot;buzz&quot;</code> to the console,</li>
	<li><code>printFizzBuzz</code> that prints the word <code>&quot;fizzbuzz&quot;</code> to the console, and</li>
	<li><code>printNumber</code> that prints a given integer to the console.</li>
</ul>

<p>You are given an instance of the class <code>FizzBuzz</code> that has four functions: <code>fizz</code>, <code>buzz</code>, <code>fizzbuzz</code> and <code>number</code>. The same instance of <code>FizzBuzz</code> will be passed to four different threads:</p>

<ul>
	<li><strong>Thread A:</strong> calls <code>fizz()</code> that should output the word <code>&quot;fizz&quot;</code>.</li>
	<li><strong>Thread B:</strong> calls <code>buzz()</code> that should output the word <code>&quot;buzz&quot;</code>.</li>
	<li><strong>Thread C:</strong> calls <code>fizzbuzz()</code> that should output the word <code>&quot;fizzbuzz&quot;</code>.</li>
	<li><strong>Thread D:</strong> calls <code>number()</code> that should only output the integers.</li>
</ul>

<p>Modify the given class to output the series <code>[1, 2, &quot;fizz&quot;, 4, &quot;buzz&quot;, ...]</code> where the <code>i<sup>th</sup></code> token (<strong>1-indexed</strong>) of the series is:</p>

<ul>
	<li><code>&quot;fizzbuzz&quot;</code> if <code>i</code> is divisible by <code>3</code> and <code>5</code>,</li>
	<li><code>&quot;fizz&quot;</code> if <code>i</code> is divisible by <code>3</code> and not <code>5</code>,</li>
	<li><code>&quot;buzz&quot;</code> if <code>i</code> is divisible by <code>5</code> and not <code>3</code>, or</li>
	<li><code>i</code> if <code>i</code> is not divisible by <code>3</code> or <code>5</code>.</li>
</ul>

<p>Implement the <code>FizzBuzz</code> class:</p>

<ul>
	<li><code>FizzBuzz(int n)</code> Initializes the object with the number <code>n</code> that represents the length of the sequence that should be printed.</li>
	<li><code>void fizz(printFizz)</code> Calls <code>printFizz</code> to output <code>&quot;fizz&quot;</code>.</li>
	<li><code>void buzz(printBuzz)</code> Calls <code>printBuzz</code> to output <code>&quot;buzz&quot;</code>.</li>
	<li><code>void fizzbuzz(printFizzBuzz)</code> Calls <code>printFizzBuzz</code> to output <code>&quot;fizzbuzz&quot;</code>.</li>
	<li><code>void number(printNumber)</code> Calls <code>printnumber</code> to output the numbers.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> n = 15
<strong>Output:</strong> [1,2,"fizz",4,"buzz","fizz",7,8,"fizz","buzz",11,"fizz",13,14,"fizzbuzz"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> n = 5
<strong>Output:</strong> [1,2,"fizz",4,"buzz"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 50</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```java
class FizzBuzz {
    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    private Semaphore fSema = new Semaphore(0);
    private Semaphore bSema = new Semaphore(0);
    private Semaphore fbSema = new Semaphore(0);
    private Semaphore nSema = new Semaphore(1);

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i = i + 3) {
            if (i % 5 != 0) {
                fSema.acquire();
                printFizz.run();
                nSema.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i = i + 5) {
            if (i % 3 != 0) {
                bSema.acquire();
                printBuzz.run();
                nSema.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i = i + 15) {
            fbSema.acquire();
            printFizzBuzz.run();
            nSema.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            nSema.acquire();
            if (i % 3 == 0 && i % 5 == 0) {
                fbSema.release();
            } else if (i % 3 == 0) {
                fSema.release();
            } else if (i % 5 == 0) {
                bSema.release();
            } else {
                printNumber.accept(i);
                nSema.release();
            }
        }
    }
}
```

```cpp
class FizzBuzz {
private:
    std::mutex mtx;
    atomic<int> index;
    int n;

    // 这里主要运用到了C++11中的RAII锁(lock_guard)的知识。
    // 需要强调的一点是，在进入循环后，要时刻不忘加入index <= n的逻辑
public:
    FizzBuzz(int n) {
        this->n = n;
        index = 1;
    }

    void fizz(function<void()> printFizz) {
        while (index <= n) {
            std::lock_guard<std::mutex> lk(mtx);
            if (0 == index % 3 && 0 != index % 5 && index <= n) {
                printFizz();
                index++;
            }
        }
    }

    void buzz(function<void()> printBuzz) {
        while (index <= n) {
            std::lock_guard<std::mutex> lk(mtx);
            if (0 == index % 5 && 0 != index % 3 && index <= n) {
                printBuzz();
                index++;
            }
        }
    }

    void fizzbuzz(function<void()> printFizzBuzz) {
        while (index <= n) {
            std::lock_guard<std::mutex> lk(mtx);
            if (0 == index % 15 && index <= n) {
                printFizzBuzz();
                index++;
            }
        }
    }

    void number(function<void(int)> printNumber) {
        while (index <= n) {
            std::lock_guard<std::mutex> lk(mtx);
            if (0 != index % 3 && 0 != index % 5 && index <= n) {
                printNumber(index);
                index++;
            }
        }
    }
};
```

<!-- tabs:end -->

<!-- end -->

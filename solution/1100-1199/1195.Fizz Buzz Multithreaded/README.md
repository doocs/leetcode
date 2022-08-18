# [1195. 交替打印字符串](https://leetcode.cn/problems/fizz-buzz-multithreaded)

[English Version](/solution/1100-1199/1195.Fizz%20Buzz%20Multithreaded/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：</p>

<ul>
	<li>如果这个数字可以被 3 整除，输出 "fizz"。</li>
	<li>如果这个数字可以被 5 整除，输出 "buzz"。</li>
	<li>如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。</li>
</ul>

<p>例如，当 <code>n = 15</code>，输出： <code>1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz</code>。</p>

<p>假设有这么一个类：</p>

<pre>
class FizzBuzz {
  public FizzBuzz(int n) { ... }               // constructor
  public void fizz(printFizz) { ... }          // only output "fizz"
  public void buzz(printBuzz) { ... }          // only output "buzz"
  public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
  public void number(printNumber) { ... }      // only output the numbers
}</pre>

<p>请你实现一个有四个线程的多线程版  <code>FizzBuzz</code>， 同一个 <code>FizzBuzz</code> 实例会被如下四个线程使用：</p>

<ol>
	<li>线程A将调用 <code>fizz()</code> 来判断是否能被 3 整除，如果可以，则输出 <code>fizz</code>。</li>
	<li>线程B将调用 <code>buzz()</code> 来判断是否能被 5 整除，如果可以，则输出 <code>buzz</code>。</li>
	<li>线程C将调用 <code>fizzbuzz()</code> 来判断是否同时能被 3 和 5 整除，如果可以，则输出 <code>fizzbuzz</code>。</li>
	<li>线程D将调用 <code>number()</code> 来实现输出既不能被 3 整除也不能被 5 整除的数字。</li>
</ol>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>本题已经提供了打印字符串的相关方法，如 <code>printFizz()</code> 等，具体方法名请参考答题模板中的注释部分。</li>
</ul>

<p> </p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **C++**

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

### **...**

```

```

<!-- tabs:end -->

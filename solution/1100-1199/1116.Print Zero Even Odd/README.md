## 1116.Print Zero Even Odd（leetcode未翻译）
### 问题描述

Suppose you are given the following code:

```
class ZeroEvenOdd {
  public ZeroEvenOdd(int n) { ... }      // constructor
  public void zero(printNumber) { ... }  // only output 0's
  public void even(printNumber) { ... }  // only output even numbers
  public void odd(printNumber) { ... }   // only output odd numbers
}
```

The same instance of `ZeroEvenOdd` will be passed to three different threads:

1. Thread A will call `zero()` which should only output 0's.
2. Thread B will call `even()` which should only ouput even numbers.
3. Thread C will call `odd()` which should only output odd numbers.

Each of the thread is given a `printNumber` method to output an integer. Modify the given program to output the series `010203040506`... where the length of the series must be 2*n*.

 

**Example 1:**

```
Input: n = 2
Output: "0102"
Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls even(), and the last one calls odd(). "0102" is the correct output.
```

**Example 2:**

```
Input: n = 5
Output: "0102030405"
```



### 思路

一个同步问题，一个互斥问题，奇偶打印是互斥，0和奇/偶的打印是同步问题，所以用到3把锁

```cpp
class ZeroEvenOdd {
private:
    int n;
    int flag;
    mutex m1,m2,m3;

public:
    ZeroEvenOdd(int n) {
        this->n = n;
        flag = 1; //奇偶判断
        m1.lock();
        m2.lock();
        m3.lock();
    }

    // printNumber(x) outputs "x", where x is an integer.
    void zero(function<void(int)> printNumber) {
        m3.unlock();
        for(int i = 0; i < n ;i++){
            m3.lock();
            printNumber(0);
            if(flag == 1)flag = 0,m2.unlock();  
            else flag = 1,m1.unlock();           
        }
    }
    

    void odd(function<void(int)> printNumber) { //输出奇数
        for(int i = 1;i <= n; i+=2){
            m2.lock();
            printNumber(i);
            m3.unlock();
        }
    }

    void even(function<void(int)> printNumber) { //输出偶数
        for(int i = 2;i <= n; i+=2){
            m1.lock();
            printNumber(i);
            m3.unlock();
        }
    }
};
```


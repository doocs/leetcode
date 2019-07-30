## 1114.按顺序打印

### 问题描述

我们提供了一个类：

```
public class Foo {
  public void one() { print("one"); }
  public void two() { print("two"); }
  public void three() { print("three"); }
}
```

三个不同的线程将会共用一个 `Foo` 实例。

- 线程 A 将会调用 `one()` 方法
- 线程 B 将会调用 `two()` 方法
- 线程 C 将会调用 `three()` 方法

请设计修改程序，以确保 `two()` 方法在 `one()` 方法之后被执行，`three()` 方法在 `two()` 方法之后被执行。

 

**示例 1:**

```
输入: [1,2,3]
输出: "onetwothree"
解释: 
有三个线程会被异步启动。
输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
正确的输出是 "onetwothree"。
```

**示例 2:**

```
输入: [1,3,2]
输出: "onetwothree"
解释: 
输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
正确的输出是 "onetwothree"。
```

### 解法

多线程同步问题，很好理解。

#### CPP

```cpp
class Foo { //C++ 11
public:
    Foo() {
        _mutex1.lock();
        _mutex2.lock();
    }

    void first(function<void()> printFirst) {
        
        printFirst();
        _mutex1.unlock();
    }

    void second(function<void()> printSecond) {
        
        _mutex1.lock();
        printSecond();
        _mutex1.unlock();
        _mutex2.unlock();
    }

    void third(function<void()> printThird) {
        
        _mutex2.lock();
        printThird();
        _mutex2.unlock();
    }
    
    
private:
    std::mutex _mutex1;
    std::mutex _mutex2;
    
};
```


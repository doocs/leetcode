## 1115.交替打印FooBar
### 问题描述

我们提供一个类：

```
class FooBar {
  public void foo() {
    for (int i = 0; i < n; i++) {
      print("foo");
    }
  }

  public void bar() {
    for (int i = 0; i < n; i++) {
      print("bar");
    }
  }
}
```

两个不同的线程将会共用一个 `FooBar` 实例。其中一个线程将会调用 `foo()` 方法，另一个线程将会调用 `bar()` 方法。

请设计修改程序，以确保 "foobar" 被输出 n 次。

 

**示例 1:**

```
输入: n = 1
输出: "foobar"
解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
```

**示例 2:**

```
输入: n = 2
输出: "foobarfoobar"
解释: "foobar" 将被输出两次。
```



### 思路

两锁交替的同步，后进行的代码先加锁原则

```cpp
class FooBar {
private:
    int n;
    std::mutex _mutex1;
    std::mutex _mutex2;

public:
    FooBar(int n) {
        this->n = n;
        _mutex2.lock();
    }

    void foo(function<void()> printFoo) {
        
        for (int i = 0; i < n; i++) {
            _mutex1.lock();
        	printFoo();
            _mutex2.unlock();
        }
    }

    void bar(function<void()> printBar) {
        
        for (int i = 0; i < n; i++) {
            _mutex2.lock();
        	printBar();
            _mutex1.unlock();
        }
    }
};
```


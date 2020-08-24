# [359. 日志速率限制器](https://leetcode-cn.com/problems/logger-rate-limiter)

[English Version](/solution/0300-0399/0359.Logger%20Rate%20Limiter/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>请你设计一个日志系统，可以流式接收日志以及它的时间戳。</p>

<p>该日志会被打印出来，需要满足一个条件：当且仅当日志内容 <strong>在过去的 10 秒钟内没有被打印过</strong>。</p>

<p>给你一条日志的内容和它的时间戳（粒度为秒级），如果这条日志在给定的时间戳应该被打印出来，则返回 true，否则请返回 false。</p>

<p>要注意的是，可能会有多条日志在同一时间被系统接收。</p>

<p><strong>示例：</strong></p>

<pre>Logger logger = new Logger();

// 日志内容 "foo" 在时刻 1 到达系统
logger.shouldPrintMessage(1, "foo"); returns true; 

// 日志内容 "bar" 在时刻 2 到达系统
logger.shouldPrintMessage(2,"bar"); returns true;

// 日志内容 "foo" 在时刻 3 到达系统
logger.shouldPrintMessage(3,"foo"); returns false;

// 日志内容 "bar" 在时刻 8 到达系统
logger.shouldPrintMessage(8,"bar"); returns false;

// 日志内容 "foo" 在时刻 10 到达系统
logger.shouldPrintMessage(10,"foo"); returns false;

// 日志内容 "foo" 在时刻 11 到达系统
logger.shouldPrintMessage(11,"foo"); returns true;
</pre>



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

### **...**
```

```

<!-- tabs:end -->
# [379. 电话目录管理系统](https://leetcode-cn.com/problems/design-phone-directory)

[English Version](/solution/0300-0399/0379.Design%20Phone%20Directory/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>设计一个电话目录管理系统，让它支持以下功能：</p>

<ol>
	<li><code>get</code>: 分配给用户一个未被使用的电话号码，获取失败请返回 -1</li>
	<li><code>check</code>: 检查指定的电话号码是否被使用</li>
	<li><code>release</code>: 释放掉一个电话号码，使其能够重新被分配</li>
</ol>

<p> </p>

<p><strong>示例：</strong></p>

<pre>// 初始化电话目录，包括 3 个电话号码：0，1 和 2。
PhoneDirectory directory = new PhoneDirectory(3);

// 可以返回任意未分配的号码，这里我们假设它返回 0。
directory.get();

// 假设，函数返回 1。
directory.get();

// 号码 2 未分配，所以返回为 true。
directory.check(2);

// 返回 2，分配后，只剩一个号码未被分配。
directory.get();

// 此时，号码 2 已经被分配，所以返回 false。
directory.check(2);

// 释放号码 2，将该号码变回未分配状态。
directory.release(2);

// 号码 2 现在是未分配状态，所以返回 true。
directory.check(2);
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= maxNumbers <= 10^4</code></li>
	<li><code>0 <= number < maxNumbers</code></li>
	<li>调用方法的总数处于区间 <code>[0 - 20000]</code> 之内</li>
</ul>



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
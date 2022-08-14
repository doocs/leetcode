# [1604. 警告一小时内使用相同员工卡大于等于三次的人](https://leetcode.cn/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period)

[English Version](/solution/1600-1699/1604.Alert%20Using%20Same%20Key-Card%20Three%20or%20More%20Times%20in%20a%20One%20Hour%20Period/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>力扣公司的员工都使用员工卡来开办公室的门。每当一个员工使用一次他的员工卡，安保系统会记录下员工的名字和使用时间。如果一个员工在一小时时间内使用员工卡的次数大于等于三次，这个系统会自动发布一个 <strong>警告</strong> 。</p>

<p>给你字符串数组 <code>keyName</code> 和 <code>keyTime</code> ，其中 <code>[keyName[i], keyTime[i]]</code> 对应一个人的名字和他在 <strong>某一天</strong> 内使用员工卡的时间。</p>

<p>使用时间的格式是 <strong>24小时制</strong> ，形如<strong> "HH:MM"</strong> ，比方说 <code>"23:51"</code> 和 <code>"09:49"</code> 。</p>

<p>请你返回去重后的收到系统警告的员工名字，将它们按 <strong>字典序</strong><strong>升序 </strong>排序后返回。</p>

<p>请注意 <code>"10:00"</code> - <code>"11:00"</code> 视为一个小时时间范围内，而 <code>"23:51"</code> - <code>"00:10"</code> 不被视为一小时内，因为系统记录的是某一天内的使用情况。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"], keyTime = ["10:00","10:40","11:00","09:00","11:00","13:00","15:00"]
<strong>输出：</strong>["daniel"]
<strong>解释：</strong>"daniel" 在一小时内使用了 3 次员工卡（"10:00"，"10:40"，"11:00"）。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>keyName = ["alice","alice","alice","bob","bob","bob","bob"], keyTime = ["12:01","12:00","18:00","21:00","21:20","21:30","23:00"]
<strong>输出：</strong>["bob"]
<strong>解释：</strong>"bob" 在一小时内使用了 3 次员工卡（"21:00"，"21:20"，"21:30"）。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>keyName = ["john","john","john"], keyTime = ["23:58","23:59","00:01"]
<strong>输出：</strong>[]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>keyName = ["leslie","leslie","leslie","clare","clare","clare","clare"], keyTime = ["13:00","13:20","14:00","18:00","18:51","19:30","19:49"]
<strong>输出：</strong>["clare","leslie"]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= keyName.length, keyTime.length <= 10<sup>5</sup></code></li>
	<li><code>keyName.length == keyTime.length</code></li>
	<li><code>keyTime</code> 格式为 <strong>"HH:MM" </strong>。</li>
	<li>保证 <code>[keyName[i], keyTime[i]]</code> 形成的二元对 <strong>互不相同 </strong>。</li>
	<li><code>1 <= keyName[i].length <= 10</code></li>
	<li><code>keyName[i]</code> 只包含小写英文字母。</li>
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

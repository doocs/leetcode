# [381. O(1) 时间插入、删除和获取随机元素 - 允许重复](https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed)

## 题目描述
<!-- 这里写题目描述 -->
<p>设计一个支持在<em>平均&nbsp;</em>时间复杂度&nbsp;<strong>O(1)&nbsp;</strong>下<strong>，&nbsp;</strong>执行以下操作的数据结构。</p>

<p><strong>注意: 允许出现重复元素。</strong></p>

<ol>
	<li><code>insert(val)</code>：向集合中插入元素 val。</li>
	<li><code>remove(val)</code>：当 val 存在时，从集合中移除一个 val。</li>
	<li><code>getRandom</code>：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。</li>
</ol>

<p><strong>示例:</strong></p>

<pre>// 初始化一个空的集合。
RandomizedCollection collection = new RandomizedCollection();

// 向集合中插入 1 。返回 true 表示集合不包含 1 。
collection.insert(1);

// 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
collection.insert(1);

// 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
collection.insert(2);

// getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
collection.getRandom();

// 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
collection.remove(1);

// getRandom 应有相同概率返回 1 和 2 。
collection.getRandom();
</pre>



## 解法
<!-- 这里可写通用的实现逻辑 -->


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### ...
```

```

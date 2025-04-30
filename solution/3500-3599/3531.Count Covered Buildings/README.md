---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3531.Count%20Covered%20Buildings/README.md
tags:
    - 数组
    - 哈希表
    - 排序
---

<!-- problem:start -->

# [3531. 统计被覆盖的建筑](https://leetcode.cn/problems/count-covered-buildings)

[English Version](/solution/3500-3599/3531.Count%20Covered%20Buildings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数 <code>n</code>，表示一个 <code>n x n</code> 的城市，同时给定一个二维数组 <code>buildings</code>，其中 <code>buildings[i] = [x, y]</code> 表示位于坐标 <code>[x, y]</code> 的一个&nbsp;<strong>唯一&nbsp;</strong>建筑。</p>

<p>如果一个建筑在四个方向（左、右、上、下）中每个方向上都至少存在一个建筑，则称该建筑&nbsp;<strong>被覆盖&nbsp;</strong>。</p>

<p>返回&nbsp;<strong>被覆盖&nbsp;</strong>的建筑数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3531.Count%20Covered%20Buildings/images/1745660407-qtNUjI-telegram-cloud-photo-size-5-6212982906394101085-m.jpg" style="width: 200px; height: 204px;" /></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 3, buildings = [[1,2],[2,2],[3,2],[2,1],[2,3]]</span></p>

<p><strong>输出:</strong> <span class="example-io">1</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>只有建筑 <code>[2,2]</code> 被覆盖，因为它在每个方向上都至少存在一个建筑：

    <ul>
    	<li>上方 (<code>[1,2]</code>)</li>
    	<li>下方 (<code>[3,2]</code>)</li>
    	<li>左方 (<code>[2,1]</code>)</li>
    	<li>右方 (<code>[2,3]</code>)</li>
    </ul>
    </li>
    <li>因此，被覆盖的建筑数量是 1。</li>

</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3531.Count%20Covered%20Buildings/images/1745660407-tUMUKl-telegram-cloud-photo-size-5-6212982906394101086-m.jpg" style="width: 200px; height: 204px;" /></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 3, buildings = [[1,1],[1,2],[2,1],[2,2]]</span></p>

<p><strong>输出:</strong> <span class="example-io">0</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>没有任何一个建筑在每个方向上都有至少一个建筑。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3531.Count%20Covered%20Buildings/images/1745660407-bQIwBX-telegram-cloud-photo-size-5-6248862251436067566-x.jpg" style="width: 202px; height: 205px;" /></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 5, buildings = [[1,3],[3,2],[3,3],[3,5],[5,3]]</span></p>

<p><strong>输出:</strong> <span class="example-io">1</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>只有建筑 <code>[3,3]</code> 被覆盖，因为它在每个方向上至少存在一个建筑：

    <ul>
    	<li>上方 (<code>[1,3]</code>)</li>
    	<li>下方 (<code>[5,3]</code>)</li>
    	<li>左方 (<code>[3,2]</code>)</li>
    	<li>右方 (<code>[3,5]</code>)</li>
    </ul>
    </li>
    <li>因此，被覆盖的建筑数量是 1。</li>

</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= buildings.length &lt;= 10<sup>5</sup></code></li>
	<li><code>buildings[i] = [x, y]</code></li>
	<li><code>1 &lt;= x, y &lt;= n</code></li>
	<li><code>buildings</code> 中所有坐标均&nbsp;<strong>唯一&nbsp;</strong>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

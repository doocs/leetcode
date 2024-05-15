---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2053.%20%E5%AE%88%E6%8A%A4%E5%A4%AA%E7%A9%BA%E5%9F%8E/README.md
---

# [LCP 53. 守护太空城](https://leetcode.cn/problems/EJvmW4)

## 题目描述

<!-- 这里写题目描述 -->

<p>各位勇者请注意，力扣太空城发布陨石雨红色预警。</p>

<p>太空城中的一些舱室将要受到陨石雨的冲击，这些舱室按照编号 <code>0 ~ N</code>&nbsp;的顺序依次排列。为了阻挡陨石损毁舱室，太空城可以使用能量展开防护屏障，具体消耗如下：</p>

<ul>
	<li>选择一个舱室开启屏障，能量消耗为 <code>2</code></li>
	<li>选择相邻两个舱室开启联合屏障，能量消耗为 <code>3</code></li>
	<li>对于已开启的&nbsp;<strong>一个&nbsp;</strong>屏障，<strong>多维持一时刻</strong>，能量消耗为 <code>1</code></li>
</ul>

<p>已知陨石雨的影响范围和到达时刻，<code>time[i]</code>&nbsp;和 <code>position[i]</code>&nbsp;分别表示该陨石的到达时刻和冲击位置。请返回太空舱能够守护所有舱室所需要的最少能量。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>同一时间，一个舱室不能被多个屏障覆盖</li>
	<li>陨石雨仅在到达时刻对冲击位置处的舱室有影响</li>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
输入：time = [1,2,1], position = [6,3,3]

输出：5

解释：时刻 1，分别开启编号 3、6 舱室的屏障，能量消耗 2*2 = 4。时刻 2，维持编号 3 舱室的屏障，能量消耗 1。因此，最少需要能量 5。
</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre>
输入：time = [1,1,1,2,2,3,5], position = [1,2,3,1,2,1,3]

输出：9

解释：时刻 1，开启编号 1、2 舱室的联合屏障，能量消耗 3。时刻 1，开启编号 3 舱室的屏障，能量消耗 2 。时刻 2，维持编号 1、2 舱室的联合屏障，能量消耗 1。时刻 3，维持编号 1、2 舱室的联合屏障，能量消耗 1。时刻 5，重新开启编号 3 舱室的屏障，能量消耗 2。因此，最少需要能量 9。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= time.length == position.length &lt;= 500</code></li>
	<li><code>1 &lt;= time[i] &lt;= 5</code></li>
	<li><code>0 &lt;= position[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- end -->

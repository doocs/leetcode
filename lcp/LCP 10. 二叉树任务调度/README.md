# [LCP 10. 二叉树任务调度](https://leetcode.cn/problems/er-cha-shu-ren-wu-diao-du)

## 题目描述

<!-- 这里写题目描述 -->

<p>任务调度优化是计算机性能优化的关键任务之一。在任务众多时，不同的调度策略可能会得到不同的总体执行时间，因此寻求一个最优的调度方案是非常有必要的。</p>

<p>通常任务之间是存在依赖关系的，即对于某个任务，你需要先<strong>完成</strong>他的前导任务（如果非空），才能开始执行该任务。<strong>我们保证任务的依赖关系是一棵二叉树，</strong>其中 <code>root</code> 为根任务，<code>root.left</code> 和 <code>root.right</code> 为他的两个前导任务（可能为空），<code>root.val</code> 为其自身的执行时间。</p>

<p>在一个 CPU 核执行某个任务时，我们可以在任何时刻暂停当前任务的执行，并保留当前执行进度。在下次继续执行该任务时，会从之前停留的进度开始继续执行。暂停的时间可以不是整数。</p>

<p>现在，系统有<strong>两个</strong> CPU 核，即我们可以同时执行两个任务，但是同一个任务不能同时在两个核上执行。给定这颗任务树，请求出所有任务执行完毕的最小时间。</p>

<p><strong>示例 1：</strong></p>

<blockquote>
<p><img alt="image.png" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2010.%20二叉树任务调度/images/3522fbf8ce4ebb20b79019124eb9870109fdfe97fe9da99f6c20c07ceb1c60b3-image.png" /></p>

<p>输入：root = [47, 74, 31]</p>

<p>输出：121</p>

<p>解释：根节点的左右节点可以并行执行31分钟，剩下的43+47分钟只能串行执行，因此总体执行时间是121分钟。</p>
</blockquote>

<p><strong>示例 2：</strong></p>

<blockquote>
<p><img alt="image.png" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2010.%20二叉树任务调度/images/13accf172ee4a660d241e25901595d55b759380b090890a17e6e7bd51a143e3f-image.png" /></p>

<p>输入：root = [15, 21, null, 24, null, 27, 26]</p>

<p>输出：87</p>
</blockquote>

<p><strong>示例 3：</strong></p>

<blockquote>
<p><img alt="image.png" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2010.%20二叉树任务调度/images/bef743a12591aafb9047dd95d335b8083dfa66e8fdedc63f50fd406b4a9d163a-image.png" /></p>

<p>输入：root = [1,3,2,null,null,4,4]</p>

<p>输出：7.5</p>
</blockquote>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= 节点数量 &lt;= 1000</code></li>
	<li><code>1 &lt;= 单节点执行时间 &lt;= 1000</code></li>
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

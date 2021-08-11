# [1101. 彼此熟识的最早时间](https://leetcode-cn.com/problems/the-earliest-moment-when-everyone-become-friends)

[English Version](/solution/1100-1199/1101.The%20Earliest%20Moment%20When%20Everyone%20Become%20Friends/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个社交圈子当中，有&nbsp;<code>N</code>&nbsp;个人。每个人都有一个从&nbsp;<code>0</code> 到&nbsp;<code>N-1</code>&nbsp;唯一的 id&nbsp;编号。</p>

<p>我们有一份日志列表&nbsp;<code>logs</code>，其中每条记录都包含一个非负整数的时间戳，以及分属两个人的不同&nbsp;id，<code>logs[i] = [timestamp, id_A, id_B]</code>。</p>

<p>每条日志标识出两个人成为好友的时间，友谊是相互的：如果 A 和 B 是好友，那么 B 和 A 也是好友。</p>

<p>如果 A 是 B 的好友，或者 A 是 B 的好友的好友，那么就可以认为 A 也与 B 熟识。</p>

<p>返回圈子里所有人之间都熟识的最早时间。如果找不到最早时间，就返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], N = 6
<strong>输出：</strong>20190301
<strong>解释：</strong>
第一次结交发生在 timestamp = 20190101，0 和 1 成为好友，社交朋友圈如下 [0,1], [2], [3], [4], [5]。
第二次结交发生在 timestamp = 20190104，3 和 4 成为好友，社交朋友圈如下 [0,1], [2], [3,4], [5].
第三次结交发生在 timestamp = 20190107，2 和 3 成为好友，社交朋友圈如下 [0,1], [2,3,4], [5].
第四次结交发生在 timestamp = 20190211，1 和 5 成为好友，社交朋友圈如下 [0,1,5], [2,3,4].
第五次结交发生在 timestamp = 20190224，2 和 4 已经是好友了。
第六次结交发生在 timestamp = 20190301，0 和 3 成为好友，大家都互相熟识了。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= N &lt;= 100</code></li>
	<li><code>1 &lt;= logs.length &lt;= 10^4</code></li>
	<li><code>0 &lt;= logs[i][0] &lt;= 10^9</code></li>
	<li><code>0 &lt;= logs[i][1], logs[i][2] &lt;= N - 1</code></li>
	<li>保证 <code>logs[i][0]</code> 中的所有时间戳都不同</li>
	<li><code>Logs</code>&nbsp;不一定按某一标准排序</li>
	<li><code>logs[i][1] != logs[i][2]</code></li>
</ol>


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
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 1, right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (canCarry(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    public boolean canCarry(int[] weights, int days, int carry) {
        int useDay = 1;
        int curCarry = 0;
        for (int weight : weights) {
            if (weight > carry) {
                return false;
            }
            if ((carry - curCarry) >= weight) {
                curCarry += weight;
            } else {
                curCarry = weight;
                useDay++;
            }
        }
        return useDay <= days;
    }
}
```

### **...**

```

```

<!-- tabs:end -->

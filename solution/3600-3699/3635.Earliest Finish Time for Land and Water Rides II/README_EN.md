---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3635.Earliest%20Finish%20Time%20for%20Land%20and%20Water%20Rides%20II/README_EN.md
rating: 1869
source: Biweekly Contest 162 Q3
tags:
    - Greedy
    - Array
    - Two Pointers
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [3635. Earliest Finish Time for Land and Water Rides II](https://leetcode.com/problems/earliest-finish-time-for-land-and-water-rides-ii)

[中文文档](/solution/3600-3699/3635.Earliest%20Finish%20Time%20for%20Land%20and%20Water%20Rides%20II/README.md)

## Description

<!-- description:start -->

<p data-end="143" data-start="53">You are given two categories of theme park attractions: <strong data-end="122" data-start="108">land rides</strong> and <strong data-end="142" data-start="127">water rides</strong>.</p>

<ul>
	<li data-end="163" data-start="147"><strong data-end="161" data-start="147">Land rides</strong>

    <ul>
    	<li data-end="245" data-start="168"><code data-end="186" data-start="168">landStartTime[i]</code> &ndash; the earliest time the <code>i<sup>th</sup></code> land ride can be boarded.</li>
    	<li data-end="306" data-start="250"><code data-end="267" data-start="250">landDuration[i]</code> &ndash; how long the <code>i<sup>th</sup></code> land ride lasts.</li>
    </ul>
    </li>
    <li><strong data-end="325" data-start="310">Water rides</strong>
    <ul>
    	<li><code data-end="351" data-start="332">waterStartTime[j]</code> &ndash; the earliest time the <code>j<sup>th</sup></code> water ride can be boarded.</li>
    	<li><code data-end="434" data-start="416">waterDuration[j]</code> &ndash; how long the <code>j<sup>th</sup></code> water ride lasts.</li>
    </ul>
    </li>

</ul>

<p data-end="569" data-start="476">A tourist must experience <strong data-end="517" data-start="502">exactly one</strong> ride from <strong data-end="536" data-start="528">each</strong> category, in <strong data-end="566" data-start="550">either order</strong>.</p>

<ul>
	<li data-end="641" data-start="573">A ride may be started at its opening time or <strong data-end="638" data-start="618">any later moment</strong>.</li>
	<li data-end="715" data-start="644">If a ride is started at time <code data-end="676" data-start="673">t</code>, it finishes at time <code data-end="712" data-start="698">t + duration</code>.</li>
	<li data-end="834" data-start="718">Immediately after finishing one ride the tourist may board the other (if it is already open) or wait until it opens.</li>
</ul>

<p data-end="917" data-start="836">Return the <strong data-end="873" data-start="847">earliest possible time</strong> at which the tourist can finish both rides.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">landStartTime = [2,8], landDuration = [4,1], waterStartTime = [6], waterDuration = [3]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li data-end="181" data-start="145">Plan A (land ride 0 &rarr; water ride 0):
	<ul>
		<li data-end="272" data-start="186">Start land ride 0 at time <code data-end="234" data-start="212">landStartTime[0] = 2</code>. Finish at <code data-end="271" data-start="246">2 + landDuration[0] = 6</code>.</li>
		<li data-end="392" data-start="277">Water ride 0 opens at time <code data-end="327" data-start="304">waterStartTime[0] = 6</code>. Start immediately at <code data-end="353" data-start="350">6</code>, finish at <code data-end="391" data-start="365">6 + waterDuration[0] = 9</code>.</li>
	</ul>
	</li>
	<li data-end="432" data-start="396">Plan B (water ride 0 &rarr; land ride 1):
	<ul>
		<li data-end="526" data-start="437">Start water ride 0 at time <code data-end="487" data-start="464">waterStartTime[0] = 6</code>. Finish at <code data-end="525" data-start="499">6 + waterDuration[0] = 9</code>.</li>
		<li data-end="632" data-start="531">Land ride 1 opens at <code data-end="574" data-start="552">landStartTime[1] = 8</code>. Start at time <code data-end="593" data-start="590">9</code>, finish at <code data-end="631" data-start="605">9 + landDuration[1] = 10</code>.</li>
	</ul>
	</li>
	<li data-end="672" data-start="636">Plan C (land ride 1 &rarr; water ride 0):
	<ul>
		<li data-end="763" data-start="677">Start land ride 1 at time <code data-end="725" data-start="703">landStartTime[1] = 8</code>. Finish at <code data-end="762" data-start="737">8 + landDuration[1] = 9</code>.</li>
		<li data-end="873" data-start="768">Water ride 0 opened at <code data-end="814" data-start="791">waterStartTime[0] = 6</code>. Start at time <code data-end="833" data-start="830">9</code>, finish at <code data-end="872" data-start="845">9 + waterDuration[0] = 12</code>.</li>
	</ul>
	</li>
	<li data-end="913" data-start="877">Plan D (water ride 0 &rarr; land ride 0):
	<ul>
		<li data-end="1007" data-start="918">Start water ride 0 at time <code data-end="968" data-start="945">waterStartTime[0] = 6</code>. Finish at <code data-end="1006" data-start="980">6 + waterDuration[0] = 9</code>.</li>
		<li data-end="1114" data-start="1012">Land ride 0 opened at <code data-end="1056" data-start="1034">landStartTime[0] = 2</code>. Start at time <code data-end="1075" data-start="1072">9</code>, finish at <code data-end="1113" data-start="1087">9 + landDuration[0] = 13</code>.</li>
	</ul>
	</li>
</ul>

<p data-end="1161" data-is-last-node="" data-is-only-node="" data-start="1116">Plan A gives the earliest finish time of 9.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">landStartTime = [5], landDuration = [3], waterStartTime = [1], waterDuration = [10]</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul data-end="1589" data-start="1086">
	<li data-end="1124" data-start="1088">Plan A (water ride 0 &rarr; land ride 0):
	<ul>
		<li data-end="1219" data-start="1129">Start water ride 0 at time <code data-end="1179" data-start="1156">waterStartTime[0] = 1</code>. Finish at <code data-end="1218" data-start="1191">1 + waterDuration[0] = 11</code>.</li>
		<li data-end="1338" data-start="1224">Land ride 0 opened at <code data-end="1268" data-start="1246">landStartTime[0] = 5</code>. Start immediately at <code data-end="1295" data-start="1291">11</code> and finish at <code data-end="1337" data-start="1310">11 + landDuration[0] = 14</code>.</li>
	</ul>
	</li>
	<li data-end="1378" data-start="1342">Plan B (land ride 0 &rarr; water ride 0):
	<ul>
		<li data-end="1469" data-start="1383">Start land ride 0 at time <code data-end="1431" data-start="1409">landStartTime[0] = 5</code>. Finish at <code data-end="1468" data-start="1443">5 + landDuration[0] = 8</code>.</li>
		<li data-end="1589" data-start="1474">Water ride 0 opened at <code data-end="1520" data-start="1497">waterStartTime[0] = 1</code>. Start immediately at <code data-end="1546" data-start="1543">8</code> and finish at <code data-end="1588" data-start="1561">8 + waterDuration[0] = 18</code>.</li>
	</ul>
	</li>
</ul>

<p data-end="1640" data-is-last-node="" data-is-only-node="" data-start="1591">Plan A provides the earliest finish time of 14.<strong>​​​​​​​</strong></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-end="38" data-start="16"><code data-end="36" data-start="16">1 &lt;= n, m &lt;= 5 * 10<sup>4</sup></code></li>
	<li data-end="93" data-start="41"><code data-end="91" data-start="41">landStartTime.length == landDuration.length == n</code></li>
	<li data-end="150" data-start="96"><code data-end="148" data-start="96">waterStartTime.length == waterDuration.length == m</code></li>
	<li data-end="237" data-start="153"><code data-end="235" data-start="153">1 &lt;= landStartTime[i], landDuration[i], waterStartTime[j], waterDuration[j] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Greedy

We can consider two orders of rides: first land rides then water rides, or first water rides then land rides.

For each order, we first calculate the earliest end time $\textit{minEnd}$ of the first type of ride, then enumerate the second type of ride and calculate the earliest end time of the second type of ride as $\max(\textit{minEnd}, \textit{startTime}) + \textit{duration}$, where $\textit{startTime}$ is the start time of the second type of ride. We take the minimum value among all possible earliest end times as the answer.

Finally, we return the minimum value between the answers of the two orders.

The time complexity is $O(n + m)$, where $n$ and $m$ are the numbers of land rides and water rides respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def earliestFinishTime(self, landStartTime: List[int], landDuration: List[int], waterStartTime: List[int], waterDuration: List[int]) -> int:
        def calc(a1, t1, a2, t2):
            min_end = min(a + t for a, t in zip(a1, t1))
            return min(max(a, min_end) + t for a, t in zip(a2, t2))

        x = calc(landStartTime, landDuration, waterStartTime, waterDuration)
        y = calc(waterStartTime, waterDuration, landStartTime, landDuration)
        return min(x, y)
```

#### Java

```java
class Solution {
    public int earliestFinishTime(
        int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int x = calc(landStartTime, landDuration, waterStartTime, waterDuration);
        int y = calc(waterStartTime, waterDuration, landStartTime, landDuration);
        return Math.min(x, y);
    }

    private int calc(int[] a1, int[] t1, int[] a2, int[] t2) {
        int minEnd = Integer.MAX_VALUE;
        for (int i = 0; i < a1.length; ++i) {
            minEnd = Math.min(minEnd, a1[i] + t1[i]);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < a2.length; ++i) {
            ans = Math.min(ans, Math.max(minEnd, a2[i]) + t2[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int earliestFinishTime(vector<int>& landStartTime, vector<int>& landDuration, vector<int>& waterStartTime, vector<int>& waterDuration) {
        int x = calc(landStartTime, landDuration, waterStartTime, waterDuration);
        int y = calc(waterStartTime, waterDuration, landStartTime, landDuration);
        return min(x, y);
    }

    int calc(vector<int>& a1, vector<int>& t1, vector<int>& a2, vector<int>& t2) {
        int minEnd = INT_MAX;
        for (int i = 0; i < a1.size(); ++i) {
            minEnd = min(minEnd, a1[i] + t1[i]);
        }
        int ans = INT_MAX;
        for (int i = 0; i < a2.size(); ++i) {
            ans = min(ans, max(minEnd, a2[i]) + t2[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func earliestFinishTime(landStartTime []int, landDuration []int, waterStartTime []int, waterDuration []int) int {
	x := calc(landStartTime, landDuration, waterStartTime, waterDuration)
	y := calc(waterStartTime, waterDuration, landStartTime, landDuration)
	return min(x, y)
}

func calc(a1 []int, t1 []int, a2 []int, t2 []int) int {
	minEnd := math.MaxInt32
	for i := 0; i < len(a1); i++ {
		minEnd = min(minEnd, a1[i]+t1[i])
	}
	ans := math.MaxInt32
	for i := 0; i < len(a2); i++ {
		ans = min(ans, max(minEnd, a2[i])+t2[i])
	}
	return ans
}
```

#### TypeScript

```ts
function earliestFinishTime(
    landStartTime: number[],
    landDuration: number[],
    waterStartTime: number[],
    waterDuration: number[],
): number {
    const x = calc(landStartTime, landDuration, waterStartTime, waterDuration);
    const y = calc(waterStartTime, waterDuration, landStartTime, landDuration);
    return Math.min(x, y);
}

function calc(a1: number[], t1: number[], a2: number[], t2: number[]): number {
    let minEnd = Number.MAX_SAFE_INTEGER;
    for (let i = 0; i < a1.length; i++) {
        minEnd = Math.min(minEnd, a1[i] + t1[i]);
    }
    let ans = Number.MAX_SAFE_INTEGER;
    for (let i = 0; i < a2.length; i++) {
        ans = Math.min(ans, Math.max(minEnd, a2[i]) + t2[i]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2960.Count%20Tested%20Devices%20After%20Test%20Operations/README_EN.md
rating: 1169
source: Weekly Contest 375 Q1
tags:
    - Array
    - Simulation
---

<!-- problem:start -->

# [2960. Count Tested Devices After Test Operations](https://leetcode.com/problems/count-tested-devices-after-test-operations)

[中文文档](/solution/2900-2999/2960.Count%20Tested%20Devices%20After%20Test%20Operations/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>batteryPercentages</code> having length <code>n</code>, denoting the battery percentages of <code>n</code> <strong>0-indexed</strong> devices.</p>

<p>Your task is to test each device <code>i</code> <strong>in order</strong> from <code>0</code> to <code>n - 1</code>, by performing the following test operations:</p>

<ul>
	<li>If <code>batteryPercentages[i]</code> is <strong>greater</strong> than <code>0</code>:

    <ul>
    	<li><strong>Increment</strong> the count of tested devices.</li>
    	<li><strong>Decrease</strong> the battery percentage of all devices with indices <code>j</code> in the range <code>[i + 1, n - 1]</code> by <code>1</code>, ensuring their battery percentage <strong>never goes below</strong> <code>0</code>, i.e, <code>batteryPercentages[j] = max(0, batteryPercentages[j] - 1)</code>.</li>
    	<li>Move to the next device.</li>
    </ul>
    </li>
    <li>Otherwise, move to the next device without performing any test.</li>

</ul>

<p>Return <em>an integer denoting the number of devices that will be tested after performing the test operations in order.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> batteryPercentages = [1,1,2,1,3]
<strong>Output:</strong> 3
<strong>Explanation: </strong>Performing the test operations in order starting from device 0:
At device 0, batteryPercentages[0] &gt; 0, so there is now 1 tested device, and batteryPercentages becomes [1,0,1,0,2].
At device 1, batteryPercentages[1] == 0, so we move to the next device without testing.
At device 2, batteryPercentages[2] &gt; 0, so there are now 2 tested devices, and batteryPercentages becomes [1,0,1,0,1].
At device 3, batteryPercentages[3] == 0, so we move to the next device without testing.
At device 4, batteryPercentages[4] &gt; 0, so there are now 3 tested devices, and batteryPercentages stays the same.
So, the answer is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> batteryPercentages = [0,1,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Performing the test operations in order starting from device 0:
At device 0, batteryPercentages[0] == 0, so we move to the next device without testing.
At device 1, batteryPercentages[1] &gt; 0, so there is now 1 tested device, and batteryPercentages becomes [0,1,1].
At device 2, batteryPercentages[2] &gt; 0, so there are now 2 tested devices, and batteryPercentages stays the same.
So, the answer is 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == batteryPercentages.length &lt;= 100 </code></li>
	<li><code>0 &lt;= batteryPercentages[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

Assume that the current number of devices we have tested is $ans$. When testing a new device $i$, its remaining battery is $\max(0, batteryPercentages[i] - ans)$. If the remaining battery is greater than $0$, it means this device can be tested, and we need to increase $ans$ by $1$.

Finally, return $ans$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def countTestedDevices(self, batteryPercentages: List[int]) -> int:
        ans = 0
        for x in batteryPercentages:
            ans += x > ans
        return ans
```

```java
class Solution {
    public int countTestedDevices(int[] batteryPercentages) {
        int ans = 0;
        for (int x : batteryPercentages) {
            ans += x > ans ? 1 : 0;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countTestedDevices(vector<int>& batteryPercentages) {
        int ans = 0;
        for (int x : batteryPercentages) {
            ans += x > ans;
        }
        return ans;
    }
};
```

```go
func countTestedDevices(batteryPercentages []int) (ans int) {
	for _, x := range batteryPercentages {
		if x > ans {
			ans++
		}
	}
	return
}
```

```ts
function countTestedDevices(batteryPercentages: number[]): number {
    let ans = 0;
    for (const x of batteryPercentages) {
        ans += x > ans ? 1 : 0;
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn count_tested_devices(battery_percentages: Vec<i32>) -> i32 {
        let mut ans = 0;
        for x in battery_percentages {
            ans += if x > ans { 1 } else { 0 };
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

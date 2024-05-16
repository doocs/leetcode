---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2960.Count%20Tested%20Devices%20After%20Test%20Operations/README.md
rating: 1169
source: 第 375 场周赛 Q1
tags:
    - 数组
    - 模拟
---

# [2960. 统计已测试设备](https://leetcode.cn/problems/count-tested-devices-after-test-operations)

[English Version](/solution/2900-2999/2960.Count%20Tested%20Devices%20After%20Test%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 、下标从<strong> 0 </strong>开始的整数数组 <code>batteryPercentages</code> ，表示 <code>n</code> 个设备的电池百分比。</p>

<p>你的任务是按照顺序测试每个设备 <code>i</code>，执行以下测试操作：</p>

<ul>
	<li>如果 <code>batteryPercentages[i]</code> <strong>大于</strong> <code>0</code>：

    <ul>
    	<li><strong>增加</strong> 已测试设备的计数。</li>
    	<li>将下标在 <code>[i + 1, n - 1]</code> 的所有设备的电池百分比减少 <code>1</code>，确保它们的电池百分比<strong> 不会低于</strong> <code>0</code> ，即 <code>batteryPercentages[j] = max(0, batteryPercentages[j] - 1)</code>。</li>
    	<li>移动到下一个设备。</li>
    </ul>
    </li>
    <li>否则，移动到下一个设备而不执行任何测试。</li>

</ul>

<p>返回一个整数，表示按顺序执行测试操作后 <strong>已测试设备</strong> 的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>batteryPercentages = [1,1,2,1,3]
<strong>输出：</strong>3
<strong>解释：</strong>按顺序从设备 0 开始执行测试操作：
在设备 0 上，batteryPercentages[0] &gt; 0 ，现在有 1 个已测试设备，batteryPercentages 变为 [1,0,1,0,2] 。
在设备 1 上，batteryPercentages[1] == 0 ，移动到下一个设备而不进行测试。
在设备 2 上，batteryPercentages[2] &gt; 0 ，现在有 2 个已测试设备，batteryPercentages 变为 [1,0,1,0,1] 。
在设备 3 上，batteryPercentages[3] == 0 ，移动到下一个设备而不进行测试。
在设备 4 上，batteryPercentages[4] &gt; 0 ，现在有 3 个已测试设备，batteryPercentages 保持不变。
因此，答案是 3 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>batteryPercentages = [0,1,2]
<strong>输出：</strong>2
<strong>解释：</strong>按顺序从设备 0 开始执行测试操作：
在设备 0 上，batteryPercentages[0] == 0 ，移动到下一个设备而不进行测试。
在设备 1 上，batteryPercentages[1] &gt; 0 ，现在有 1 个已测试设备，batteryPercentages 变为 [0,1,1] 。
在设备 2 上，batteryPercentages[2] &gt; 0 ，现在有 2 个已测试设备，batteryPercentages 保持不变。
因此，答案是 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == batteryPercentages.length &lt;= 100 </code></li>
	<li><code>0 &lt;= batteryPercentages[i] &lt;= 100</code></li>
</ul>

## 解法

### 方法一：模拟

假设我们当前已测试的设备数量为 $ans$，当测试新的一台设备 $i$ 时，它的剩余电量为 $\max(0, batteryPercentages[i] - ans)$，如果剩余电量大于 $0$，则说明这台设备可以进行测试，此时我们需要将 $ans$ 增加 $1$。

最后返回 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

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

<!-- end -->

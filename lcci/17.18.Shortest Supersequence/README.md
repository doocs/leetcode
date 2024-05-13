# [面试题 17.18. 最短超串](https://leetcode.cn/problems/shortest-supersequence-lcci)

[English Version](/lcci/17.18.Shortest%20Supersequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。</p>
<p>返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。</p>
<p><strong>示例 1:</strong></p>
<pre><strong>输入:</strong>
big = [7,5,9,0,2,1,3,<strong>5,7,9,1</strong>,1,5,8,8,9,7]
small = [1,5,9]
<strong>输出: </strong>[7,10]</pre>
<p><strong>示例 2:</strong></p>
<pre><strong>输入:</strong>
big = [1,2,3]
small = [4]
<strong>输出: </strong>[]</pre>
<p><strong>提示：</strong></p>
<ul>
	<li><code>big.length&nbsp;&lt;= 100000</code></li>
	<li><code>1 &lt;= small.length&nbsp;&lt;= 100000</code></li>
</ul>

## 解法

### 方法一：哈希表 + 双指针

我们定义两个哈希表，其中哈希表 $need$ 用于存储数组 $small$ 中的元素及其出现次数，哈希表 $window$ 用于存储当前滑动窗口中的元素及其出现次数。另外，我们用变量 $cnt$ 记录当前未满足条件的元素个数，用变量 $mi$ 记录最短子数组的长度，用变量 $k$ 记录最短子数组的左端点。

我们用双指针 $j$ 和 $i$ 分别表示滑动窗口的左右端点，初始时，$j$ 和 $i$ 均指向数组 $big$ 的第一个元素。我们先移动右指针 $i$，将 $big[i]$ 加入到 $window$ 中，如果 $window[big[i]]$ 的值小于等于 $need[big[i]]$ 的值，说明当前滑动窗口中包含数组 $small$ 中的一个元素，令 $cnt$ 减一。当 $cnt$ 的值等于 $0$ 时，说明当前滑动窗口中包含数组 $small$ 中的所有元素，此时我们移动左指针 $j$，将 $big[j]$ 从 $window$ 中减去，如果 $window[big[j]]$ 的值小于 $need[big[j]]$ 的值，说明当前滑动窗口不再包含数组 $small$ 中的所有元素，令 $cnt$ 加一。在滑动窗口移动的过程中，我们更新 $mi$ 和 $k$ 的值。

时间复杂度 $O(m + n)$，空间复杂度 $O(m + n)$。其中 $m$ 和 $n$ 分别是数组 $big$ 和 $small$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def shortestSeq(self, big: List[int], small: List[int]) -> List[int]:
        need = Counter(small)
        window = Counter()
        cnt, j, k, mi = len(small), 0, -1, inf
        for i, x in enumerate(big):
            window[x] += 1
            if need[x] >= window[x]:
                cnt -= 1
            while cnt == 0:
                if i - j + 1 < mi:
                    mi = i - j + 1
                    k = j
                if need[big[j]] >= window[big[j]]:
                    cnt += 1
                window[big[j]] -= 1
                j += 1
        return [] if k < 0 else [k, k + mi - 1]
```

```java
class Solution {
    public int[] shortestSeq(int[] big, int[] small) {
        int cnt = small.length;
        Map<Integer, Integer> need = new HashMap<>(cnt);
        Map<Integer, Integer> window = new HashMap<>(cnt);
        for (int x : small) {
            need.put(x, 1);
        }
        int k = -1, mi = 1 << 30;
        for (int i = 0, j = 0; i < big.length; ++i) {
            window.merge(big[i], 1, Integer::sum);
            if (need.getOrDefault(big[i], 0) >= window.get(big[i])) {
                --cnt;
            }
            while (cnt == 0) {
                if (i - j + 1 < mi) {
                    mi = i - j + 1;
                    k = j;
                }
                if (need.getOrDefault(big[j], 0) >= window.get(big[j])) {
                    ++cnt;
                }
                window.merge(big[j++], -1, Integer::sum);
            }
        }
        return k < 0 ? new int[0] : new int[] {k, k + mi - 1};
    }
}
```

```cpp
class Solution {
public:
    vector<int> shortestSeq(vector<int>& big, vector<int>& small) {
        int cnt = small.size();
        unordered_map<int, int> need;
        unordered_map<int, int> window;
        for (int x : small) {
            need[x] = 1;
        }
        int k = -1, mi = 1 << 30;
        for (int i = 0, j = 0; i < big.size(); ++i) {
            window[big[i]]++;
            if (need[big[i]] >= window[big[i]]) {
                --cnt;
            }
            while (cnt == 0) {
                if (i - j + 1 < mi) {
                    mi = i - j + 1;
                    k = j;
                }
                if (need[big[j]] >= window[big[j]]) {
                    ++cnt;
                }
                window[big[j++]]--;
            }
        }
        if (k < 0) {
            return {};
        }
        return {k, k + mi - 1};
    }
};
```

```go
func shortestSeq(big []int, small []int) []int {
	cnt := len(small)
	need := map[int]int{}
	window := map[int]int{}
	for _, x := range small {
		need[x] = 1
	}
	j, k, mi := 0, -1, 1<<30
	for i, x := range big {
		window[x]++
		if need[x] >= window[x] {
			cnt--
		}
		for cnt == 0 {
			if t := i - j + 1; t < mi {
				mi = t
				k = j
			}
			if need[big[j]] >= window[big[j]] {
				cnt++
			}
			window[big[j]]--
			j++
		}
	}
	if k < 0 {
		return []int{}
	}
	return []int{k, k + mi - 1}
}
```

```ts
function shortestSeq(big: number[], small: number[]): number[] {
    let cnt = small.length;
    const need: Map<number, number> = new Map();
    const window: Map<number, number> = new Map();
    for (const x of small) {
        need.set(x, 1);
    }
    let k = -1;
    let mi = 1 << 30;
    for (let i = 0, j = 0; i < big.length; ++i) {
        window.set(big[i], (window.get(big[i]) ?? 0) + 1);
        if ((need.get(big[i]) ?? 0) >= window.get(big[i])!) {
            --cnt;
        }
        while (cnt === 0) {
            if (i - j + 1 < mi) {
                mi = i - j + 1;
                k = j;
            }
            if ((need.get(big[j]) ?? 0) >= window.get(big[j])!) {
                ++cnt;
            }
            window.set(big[j], window.get(big[j])! - 1);
            ++j;
        }
    }
    return k < 0 ? [] : [k, k + mi - 1];
}
```

```swift
class Solution {
    func shortestSeq(_ big: [Int], _ small: [Int]) -> [Int] {
        let needCount = small.count
        var need = [Int: Int]()
        var window = [Int: Int]()
        small.forEach { need[$0, default: 0] += 1 }
        
        var count = needCount
        var minLength = Int.max
        var result = (-1, -1)
        
        var left = 0
        for right in 0..<big.count {
            let element = big[right]
            if need[element] != nil {
                window[element, default: 0] += 1
                if window[element]! <= need[element]! {
                    count -= 1
                }
            }
            
            while count == 0 {
                if right - left + 1 < minLength {
                    minLength = right - left + 1
                    result = (left, right)
                }
                
                let leftElement = big[left]
                if need[leftElement] != nil {
                    window[leftElement]! -= 1
                    if window[leftElement]! < need[leftElement]! {
                        count += 1
                    }
                }
                left += 1
            }
        }
        
        return result.0 == -1 ? [] : [result.0, result.1]
    }
}
```

<!-- tabs:end -->

<!-- end -->

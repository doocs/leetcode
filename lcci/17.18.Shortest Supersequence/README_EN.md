# [17.18. Shortest Supersequence](https://leetcode.cn/problems/shortest-supersequence-lcci)

[中文文档](/lcci/17.18.Shortest%20Supersequence/README.md)

## Description

<p>You are given two arrays, one shorter (with all distinct elements) and one longer. Find the shortest subarray in the longer array that contains all the elements in the shorter array. The items can appear in any order.</p>
<p>Return the indexes of the leftmost and the rightmost elements of the array. If there are more than one answer, return the one that has the smallest left index. If there is no answer, return an empty array.</p>
<p><strong>Example 1:</strong></p>
<pre>

<strong>Input:</strong>

big = [7,5,9,0,2,1,3,<strong>5,7,9,1</strong>,1,5,8,8,9,7]

small = [1,5,9]

<strong>Output: </strong>[7,10]</pre>

<p><strong>Example 2:</strong></p>
<pre>

<strong>Input:</strong>

big = [1,2,3]

small = [4]

<strong>Output: </strong>[]</pre>

<p><strong>Note: </strong></p>
<ul>
	<li><code>big.length&nbsp;&lt;= 100000</code></li>
	<li><code>1 &lt;= small.length&nbsp;&lt;= 100000</code></li>
</ul>

## Solutions

### Solution 1

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

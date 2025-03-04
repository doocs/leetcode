---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2066.%20%E6%9C%80%E5%B0%8F%E5%B1%95%E5%8F%B0%E6%95%B0%E9%87%8F/README.md
---

<!-- problem:start -->

# [LCP 66. 最小展台数量](https://leetcode.cn/problems/600YaG)

## 题目描述

<!-- description:start -->

力扣嘉年华将举办一系列展览活动，后勤部将负责为每场展览提供所需要的展台。
已知后勤部得到了一份需求清单，记录了近期展览所需要的展台类型， `demand[i][j]` 表示第 `i` 天展览时第 `j` 个展台的类型。
在满足每一天展台需求的基础上，请返回后勤部需要准备的 **最小** 展台数量。

**注意：**

-   同一展台在不同天中可以重复使用。

**示例 1：**

> 输入：`demand = ["acd","bed","accd"]`
>
> 输出：`6`
>
> 解释：
> 第 `0` 天需要展台 `a、c、d`；
> 第 `1` 天需要展台 `b、e、d`；
> 第 `2` 天需要展台 `a、c、c、d`；
> 因此，后勤部准备 `abccde` 的展台，可以满足每天的展览需求;

**示例 2：**

> 输入：`demand = ["abc","ab","ac","b"]`
>
> 输出：`3`

**提示：**

-   `1 <= demand.length,demand[i].length <= 100`
-   `demand[i][j]` 仅为小写字母

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们用哈希表或数组 $cnt$ 记录当前可用的展台以及数量。

然后遍历 $demand$，对于每一天，遍历该天的展台需求，如果 $cnt$ 中有该展台，则将其数量减一，否则我们需要准备一个新的展台，答案加一。遍历结束后，将该天的所有展台都加入 $cnt$ 中。

最后返回答案即可。

时间复杂度 $O(L)$，空间复杂度 $O(C)$。其中 $L$ 为 $demand$ 中所有字符串的长度之和，而 $C$ 为字符集的大小，本题中 $C = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minNumBooths(self, demand: List[str]) -> int:
        cnt = Counter()
        ans = 0
        for s in demand:
            for c in s:
                if cnt[c]:
                    cnt[c] -= 1
                else:
                    ans += 1
            for c in s:
                cnt[c] += 1
        return ans
```

#### Java

```java
class Solution {
    public int minNumBooths(String[] demand) {
        int[] cnt = new int[26];
        int ans = 0;
        for (var s : demand) {
            int m = s.length();
            for (int i = 0; i < m; ++i) {
                int j = s.charAt(i) - 'a';
                if (cnt[j] > 0) {
                    --cnt[j];
                } else {
                    ++ans;
                }
            }
            for (int i = 0; i < m; ++i) {
                ++cnt[s.charAt(i) - 'a'];
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minNumBooths(vector<string>& demand) {
        int cnt[26]{};
        int ans = 0;
        for (auto& s : demand) {
            for (char& c : s) {
                if (cnt[c - 'a']) {
                    --cnt[c - 'a'];
                } else {
                    ++ans;
                }
            }
            for (char& c : s) {
                ++cnt[c - 'a'];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minNumBooths(demand []string) (ans int) {
	cnt := [26]int{}
	for _, s := range demand {
		for _, c := range s {
			if cnt[c-'a'] > 0 {
				cnt[c-'a']--
			} else {
				ans++
			}
		}
		for _, c := range s {
			cnt[c-'a']++
		}
	}
	return
}
```

#### Swift

```swift
class Solution {
    func minNumBooths(_ demand: [String]) -> Int {
        var maxBooths = [Int](repeating: 0, count: 26)

        for day in demand {
            var dailyCount = [Int](repeating: 0, count: 26)
            for char in day {
                let index = Int(char.asciiValue! - Character("a").asciiValue!)
                dailyCount[index] += 1
            }
            for i in 0..<26 {
                maxBooths[i] = max(maxBooths[i], dailyCount[i])
            }
        }

        return maxBooths.reduce(0, +)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

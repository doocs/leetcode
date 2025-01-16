---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0846.Hand%20of%20Straights/README.md
tags:
    - 贪心
    - 数组
    - 哈希表
    - 排序
---

<!-- problem:start -->

# [846. 一手顺子](https://leetcode.cn/problems/hand-of-straights)

[English Version](/solution/0800-0899/0846.Hand%20of%20Straights/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 <code>groupSize</code> ，并且由 <code>groupSize</code> 张连续的牌组成。</p>

<p>给你一个整数数组 <code>hand</code> 其中 <code>hand[i]</code> 是写在第 <code>i</code> 张牌上的<strong>数值</strong>。如果她可能重新排列这些牌，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
<strong>输出：</strong>true
<strong>解释：</strong>Alice 手中的牌可以被重新排列为 <code>[1,2,3]，[2,3,4]，[6,7,8]</code>。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>hand = [1,2,3,4,5], groupSize = 4
<strong>输出：</strong>false
<strong>解释：</strong>Alice 手中的牌无法被重新排列成几个大小为 4 的组。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= hand.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= hand[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= groupSize &lt;= hand.length</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong>此题目与 1296 重复：<a href="https://leetcode.cn/problems/divide-array-in-sets-of-k-consecutive-numbers/" target="_blank">https://leetcode.cn/problems/divide-array-in-sets-of-k-consecutive-numbers/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 排序

我们先用哈希表 `cnt` 统计数组 `hand` 中每个数字出现的次数，然后对数组 `hand` 进行排序。

接下来，我们遍历数组 `hand`，对于数组中的每个数字 $v$，如果 $v$ 在哈希表 `cnt` 中出现的次数不为 $0$，则我们枚举 $v$ 到 $v+groupSize-1$ 的每个数字，如果这些数字在哈希表 `cnt` 中出现的次数都不为 $0$，则我们将这些数字的出现次数减 $1$，如果减 $1$ 后这些数字的出现次数为 $0$，则我们在哈希表 `cnt` 中删除这些数字。否则说明无法将数组划分成若干个长度为 $groupSize$ 的子数组，返回 `false`。如果可以将数组划分成若干个长度为 $groupSize$ 的子数组，则遍历结束后返回 `true`。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `hand` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isNStraightHand(self, hand: List[int], groupSize: int) -> bool:
        cnt = Counter(hand)
        for v in sorted(hand):
            if cnt[v]:
                for x in range(v, v + groupSize):
                    if cnt[x] == 0:
                        return False
                    cnt[x] -= 1
                    if cnt[x] == 0:
                        cnt.pop(x)
        return True
```

#### Java

```java
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : hand) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        Arrays.sort(hand);
        for (int v : hand) {
            if (cnt.containsKey(v)) {
                for (int x = v; x < v + groupSize; ++x) {
                    if (!cnt.containsKey(x)) {
                        return false;
                    }
                    cnt.put(x, cnt.get(x) - 1);
                    if (cnt.get(x) == 0) {
                        cnt.remove(x);
                    }
                }
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isNStraightHand(vector<int>& hand, int groupSize) {
        unordered_map<int, int> cnt;
        for (int& v : hand) ++cnt[v];
        sort(hand.begin(), hand.end());
        for (int& v : hand) {
            if (cnt.count(v)) {
                for (int x = v; x < v + groupSize; ++x) {
                    if (!cnt.count(x)) {
                        return false;
                    }
                    if (--cnt[x] == 0) {
                        cnt.erase(x);
                    }
                }
            }
        }
        return true;
    }
};
```

#### Go

```go
func isNStraightHand(hand []int, groupSize int) bool {
	cnt := map[int]int{}
	for _, v := range hand {
		cnt[v]++
	}
	sort.Ints(hand)
	for _, v := range hand {
		if _, ok := cnt[v]; ok {
			for x := v; x < v+groupSize; x++ {
				if _, ok := cnt[x]; !ok {
					return false
				}
				cnt[x]--
				if cnt[x] == 0 {
					delete(cnt, x)
				}
			}
		}
	}
	return true
}
```

#### TypeScript

```ts
function isNStraightHand(hand: number[], groupSize: number) {
    const cnt: Record<number, number> = {};
    for (const i of hand) {
        cnt[i] = (cnt[i] ?? 0) + 1;
    }

    const keys = Object.keys(cnt).map(Number);
    for (const i of keys) {
        while (cnt[i]) {
            for (let j = i; j < groupSize + i; j++) {
                if (!cnt[j]) {
                    return false;
                }
                cnt[j]--;
            }
        }
    }

    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：有序集合

我们也可以使用有序集合统计数组 `hand` 中每个数字出现的次数。

接下来，循环取出有序集合中的最小值 $v$，然后枚举 $v$ 到 $v+groupSize-1$ 的每个数字，如果这些数字在有序集合中出现的次数都不为 $0$，则我们将这些数字的出现次数减 $1$，如果出现次数减 $1$ 后为 $0$，则将该数字从有序集合中删除，否则说明无法将数组划分成若干个长度为 $groupSize$ 的子数组，返回 `false`。如果可以将数组划分成若干个长度为 $groupSize$ 的子数组，则遍历结束后返回 `true`。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `hand` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isNStraightHand(self, hand: List[int], groupSize: int) -> bool:
        if len(hand) % groupSize != 0:
            return False
        sd = SortedDict()
        for h in hand:
            if h in sd:
                sd[h] += 1
            else:
                sd[h] = 1
        while sd:
            v = sd.peekitem(0)[0]
            for i in range(v, v + groupSize):
                if i not in sd:
                    return False
                if sd[i] == 1:
                    sd.pop(i)
                else:
                    sd[i] -= 1
        return True
```

#### Java

```java
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int h : hand) {
            tm.put(h, tm.getOrDefault(h, 0) + 1);
        }
        while (!tm.isEmpty()) {
            int v = tm.firstKey();
            for (int i = v; i < v + groupSize; ++i) {
                if (!tm.containsKey(i)) {
                    return false;
                }
                if (tm.get(i) == 1) {
                    tm.remove(i);
                } else {
                    tm.put(i, tm.get(i) - 1);
                }
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isNStraightHand(vector<int>& hand, int groupSize) {
        if (hand.size() % groupSize != 0) return false;
        map<int, int> mp;
        for (int& h : hand) mp[h] += 1;
        while (!mp.empty()) {
            int v = mp.begin()->first;
            for (int i = v; i < v + groupSize; ++i) {
                if (!mp.count(i)) return false;
                if (mp[i] == 1)
                    mp.erase(i);
                else
                    mp[i] -= 1;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isNStraightHand(hand []int, groupSize int) bool {
	if len(hand)%groupSize != 0 {
		return false
	}
	m := treemap.NewWithIntComparator()
	for _, h := range hand {
		if v, ok := m.Get(h); ok {
			m.Put(h, v.(int)+1)
		} else {
			m.Put(h, 1)
		}
	}
	for !m.Empty() {
		v, _ := m.Min()
		for i := v.(int); i < v.(int)+groupSize; i++ {
			if _, ok := m.Get(i); !ok {
				return false
			}
			if v, _ := m.Get(i); v.(int) == 1 {
				m.Remove(i)
			} else {
				m.Put(i, v.(int)-1)
			}
		}
	}
	return true
}
```

#### TypeScript

```ts
function isNStraightHand(hand: number[], groupSize: number): boolean {
    const n = hand.length;
    if (n % groupSize) {
        return false;
    }

    const groups: number[][] = Array.from({ length: n / groupSize }, () => []);
    hand.sort((a, b) => a - b);

    for (let i = 0; i < n; i++) {
        let isPushed = false;

        for (const g of groups) {
            if (g.length === groupSize || (g.length && hand[i] - g.at(-1)! !== 1)) {
                continue;
            }

            g.push(hand[i]);
            isPushed = true;
            break;
        }

        if (!isPushed) {
            return false;
        }
    }

    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

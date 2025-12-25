---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2030.%20%E9%AD%94%E5%A1%94%E6%B8%B8%E6%88%8F/README.md
---

<!-- problem:start -->

# [LCP 30. 魔塔游戏](https://leetcode.cn/problems/p0NxJO)

## 题目描述

<!-- description:start -->

小扣当前位于魔塔游戏第一层，共有 `N` 个房间，编号为 `0 ~ N-1`。每个房间的补血道具/怪物对于血量影响记于数组 `nums`，其中正数表示道具补血数值，即血量增加对应数值；负数表示怪物造成伤害值，即血量减少对应数值；`0` 表示房间对血量无影响。

**小扣初始血量为 1，且无上限**。假定小扣原计划按房间编号升序访问所有房间补血/打怪，**为保证血量始终为正值**，小扣需对房间访问顺序进行调整，**每次仅能将一个怪物房间（负数的房间）调整至访问顺序末尾**。请返回小扣最少需要调整几次，才能顺利访问所有房间。若调整顺序也无法访问完全部房间，请返回 -1。

**示例 1：**

> 输入：`nums = [100,100,100,-250,-60,-140,-50,-50,100,150]`
>
> 输出：`1`
>
> 解释：初始血量为 1。至少需要将 nums[3] 调整至访问顺序末尾以满足要求。

**示例 2：**

> 输入：`nums = [-200,-300,400,0]`
>
> 输出：`-1`
>
> 解释：调整访问顺序也无法完成全部房间的访问。

**提示：**

- `1 <= nums.length <= 10^5`
- `-10^5 <= nums[i] <= 10^5`

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 优先队列（小根堆）

我们定义以下数据结构或变量，其中：

- 优先队列（小根堆） $q$：存储当前所有怪物房间的血量减少值，堆顶元素为血量减少值最大的怪物房间（也即是最小的负数）。
- 血量 $blood$：存储当前的血量，初始时 $blood = 1$。
- 操作次数 $ans$：存储已经进行的操作次数，初始时 $ans = 0$。
- 临时变量 $v$：存储当前待减少的血量，初始时 $v = 0$。

我们按照房间编号升序遍历所有房间，对于当前遍历到的房间 $x$，如果 $x$ 为怪物房间，我们就将 $x$ 的血量减少值加入 $q$。然后，我们更新当前的血量 $blood$，即 $blood = blood + x$。如果此时 $blood \le 0$，说明当前的血量不足以通过当前房间，因此我们需要调整访问顺序，此时我们可以贪心地将 $q$ 中血量减少值最大的房间调整至访问顺序的末尾，即把它累加到临时变量 $v$ 上，然后将 $blood$ 增加对应的值，并将该房间从 $q$ 中弹出。

遍历结束后，我们需要更新当前的血量 $blood$，即 $blood = blood + v$。如果此时 $blood \le 0$，说明无法访问完所有房间，返回 $-1$。否则，返回 $ans$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$，其中 $n$ 为房间的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def magicTower(self, nums: List[int]) -> int:
        q = []
        blood = 1
        ans = v = 0
        for x in nums:
            if x < 0:
                heappush(q, x)
            blood += x
            if blood <= 0:
                ans += 1
                v += q[0]
                blood -= heappop(q)
        blood += v
        return -1 if blood <= 0 else ans
```

#### Java

```java
class Solution {
    public int magicTower(int[] nums) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        long blood = 1, v = 0;
        int ans = 0;
        for (int x : nums) {
            if (x < 0) {
                q.offer(x);
            }
            blood += x;
            if (blood <= 0) {
                ++ans;
                v += q.peek();
                blood -= q.poll();
            }
        }
        blood += v;
        return blood <= 0 ? -1 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int magicTower(vector<int>& nums) {
        priority_queue<int, vector<int>, greater<int>> q;
        int64_t blood = 1, v = 0;
        int ans = 0;
        for (int x : nums) {
            if (x < 0) {
                q.push(x);
            }
            blood += x;
            if (blood <= 0) {
                ++ans;
                v += q.top();
                blood -= q.top();
                q.pop();
            }
        }
        blood += v;
        return blood <= 0 ? -1 : ans;
    }
};
```

#### Go

```go
func magicTower(nums []int) (ans int) {
	q := hp{}
	blood, v := 1, 0
	for _, x := range nums {
		if x < 0 {
			heap.Push(&q, x)
		}
		blood += x
		if blood <= 0 {
			ans++
			t := heap.Pop(&q).(int)
			v += t
			blood -= t
		}
	}
	if blood+v <= 0 {
		return -1
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

#### TypeScript

```ts
function magicTower(nums: number[]): number {
    const q = new MinPriorityQueue<number>();
    let [ans, blood, v] = [0, 1, 0];
    for (const x of nums) {
        if (x < 0) {
            q.enqueue(x);
        }
        blood += x;
        if (blood <= 0) {
            ++ans;
            const t = q.dequeue();
            blood -= t;
            v += t;
        }
    }
    return blood + v <= 0 ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

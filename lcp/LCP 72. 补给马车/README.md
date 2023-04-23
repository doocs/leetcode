# [LCP 72. 补给马车](https://leetcode.cn/problems/hqCnmP)

## 题目描述

<!-- 这里写题目描述 -->

远征队即将开启未知的冒险之旅，不过在此之前，将对补给车队进行最后的检查。`supplies[i]` 表示编号为 `i` 的补给马车装载的物资数量。
考虑到车队过长容易被野兽偷袭，他们决定将车队的长度变为原来的一半（向下取整），计划为：

-   找出车队中 **物资之和最小** 两辆 **相邻** 马车，将它们车辆的物资整合为一辆。若存在多组物资之和相同的马车，则取编号最小的两辆马车进行整合；
-   重复上述操作直到车队长度符合要求。

请返回车队长度符合要求后，物资的分布情况。

**示例 1：**

> 输入：`supplies = [7,3,6,1,8]`
>
> 输出：`[10,15]`
>
> 解释：
> 第 1 次合并，符合条件的两辆马车为 6,1，合并后的车队为 [7,3,7,8]；
> 第 2 次合并，符合条件的两辆马车为 (7,3) 和 (3,7)，取编号最小的 (7,3)，合并后的车队为 [10,7,8]；
> 第 3 次合并，符合条件的两辆马车为 7,8，合并后的车队为 [10,15]；
> 返回 `[10,15]`

**示例 2：**

> 输入：`supplies = [1,3,1,5]`
>
> 输出：`[5,5]`

**解释：**

-   `2 <= supplies.length <= 1000`
-   `1 <= supplies[i] <= 1000`

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

根据题目描述，我们每次遍历 `supplies`，找到物资之和最小的两辆相邻马车，将它们车辆的物资整合为一辆，重复上述操作直到车队长度符合要求。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为 `supplies` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def supplyWagon(self, supplies: List[int]) -> List[int]:
        for _ in range((len(supplies) + 1) >> 1):
            n = len(supplies)
            mi = inf
            k = 0
            for i in range(n - 1):
                x = supplies[i] + supplies[i + 1]
                if mi > x:
                    mi = x
                    k = i
            t = []
            i = 0
            while i < n:
                if i == k:
                    t.append(mi)
                    i += 2
                else:
                    t.append(supplies[i])
                    i += 1
            supplies = t
        return supplies
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] supplyWagon(int[] supplies) {
        for (int h = (supplies.length + 1) >> 1; h > 0; --h) {
            int n = supplies.length;
            int mi = 1 << 30;
            int k = 0;
            for (int i = 0; i < n - 1; ++i) {
                int x = supplies[i] + supplies[i + 1];
                if (mi > x) {
                    mi = x;
                    k = i;
                }
            }
            int[] t = new int[n - 1];
            for (int i = 0, j = 0; i < n; ++i, ++j) {
                if (i == k) {
                    t[j] = mi;
                    ++i;
                } else {
                    t[j] = supplies[i];
                }
            }
            supplies = t;
        }
        return supplies;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> supplyWagon(vector<int>& supplies) {
        for (int h = (supplies.size() + 1) >> 1; h; --h) {
            int n = supplies.size();
            int mi = 1 << 30;
            int k = 0;
            for (int i = 0; i < n - 1; ++i) {
                int x = supplies[i] + supplies[i + 1];
                if (mi > x) {
                    mi = x;
                    k = i;
                }
            }
            vector<int> t(n - 1);
            for (int i = 0, j = 0; i < n; ++i, ++j) {
                if (i == k) {
                    t[j] = mi;
                    ++i;
                } else {
                    t[j] = supplies[i];
                }
            }
            supplies = move(t);
        }
        return supplies;
    }
};
```

### **Go**

```go
func supplyWagon(supplies []int) []int {
	for h := (len(supplies) + 1) >> 1; h > 0; h-- {
		n := len(supplies)
		mi := 1 << 30
		k := 0
		for i := 0; i < n-1; i++ {
			x := supplies[i] + supplies[i+1]
			if mi > x {
				mi = x
				k = i
			}
		}
		t := make([]int, n-1)
		for i, j := 0, 0; i < n; i, j = i+1, j+1 {
			if i == k {
				t[j] = mi
				i++
			} else {
				t[j] = supplies[i]
			}
		}
		supplies = t
	}
	return supplies
}
```

### **TypeScript**

```ts
function supplyWagon(supplies: number[]): number[] {
    for (let h = (supplies.length + 1) >> 1; h > 0; --h) {
        const n = supplies.length;
        let mi = 1 << 30;
        let k = 0;
        for (let i = 0; i < n - 1; ++i) {
            const x = supplies[i] + supplies[i + 1];
            if (mi > x) {
                mi = x;
                k = i;
            }
        }
        const t: number[] = new Array(n - 1);
        for (let i = 0, j = 0; i < n; ++i, ++j) {
            if (i === k) {
                t[j] = mi;
                ++i;
            } else {
                t[j] = supplies[i];
            }
        }
        supplies = t;
    }
    return supplies;
}
```

### **...**

```

```

<!-- tabs:end -->

---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [LCP 73. 探险营地](https://leetcode.cn/problems/0Zeoeg)

## 题目描述

<!-- description:start -->

探险家小扣的行动轨迹，都将保存在记录仪中。`expeditions[i]` 表示小扣第 `i` 次探险记录，用一个字符串数组表示。其中的每个「营地」由大小写字母组成，通过子串 `->` 连接。

> 例："Leet->code->Campsite"，表示到访了 "Leet"、"code"、"Campsite" 三个营地。

`expeditions[0]` 包含了初始小扣已知的所有营地；对于之后的第 `i` 次探险(即 `expeditions[i]` 且 i > 0)，如果记录中包含了之前均没出现的营地，则表示小扣 **新发现** 的营地。

请你找出小扣发现新营地最多且索引最小的那次探险，并返回对应的记录索引。如果所有探险记录都没有发现新的营地，返回 `-1`

**注意：**

- 大小写不同的营地视为不同的营地；
- 营地的名称长度均大于 `0`。

**示例 1：**

> 输入：`expeditions = ["leet->code","leet->code->Campsite->Leet","leet->code->leet->courier"]`
>
> 输出：`1`
>
> 解释：
> 初始已知的所有营地为 "leet" 和 "code"
> 第 1 次，到访了 "leet"、"code"、"Campsite"、"Leet"，新发现营地 2 处："Campsite"、"Leet"
> 第 2 次，到访了 "leet"、"code"、"courier"，新发现营地 1 处："courier"
> 第 1 次探险发现的新营地数量最多，因此返回 `1`

**示例 2：**

> 输入：`expeditions = ["Alice->Dex","","Dex"]`
>
> 输出：`-1`
>
> 解释：
> 初始已知的所有营地为 "Alice" 和 "Dex"
> 第 1 次，未到访任何营地；
> 第 2 次，到访了 "Dex"，未新发现营地；
> 因为两次探险均未发现新的营地，返回 `-1`

**示例 3：**

> 输入：`expeditions = ["","Gryffindor->Slytherin->Gryffindor","Hogwarts->Hufflepuff->Ravenclaw"]`
>
> 输出：`2`
>
> 解释：
> 初始未发现任何营地；
> 第 1 次，到访 "Gryffindor"、"Slytherin" 营地，其中重复到访 "Gryffindor" 两次，
> 因此新发现营地为 2 处："Gryffindor"、"Slytherin"
> 第 2 次，到访 "Hogwarts"、"Hufflepuff"、"Ravenclaw" 营地；
> 新发现营地 3 处："Hogwarts"、"Hufflepuff"、"Ravenclaw"；
> 第 2 次探险发现的新营地数量最多，因此返回 `2`

**提示：**

- `1 <= expeditions.length <= 1000`
- `0 <= expeditions[i].length <= 1000`
- 探险记录中只包含大小写字母和子串"->"

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希集合

<!-- thinking:start -->

> **思考**
>
> 需要找出新发现营地最多且索引最小的那次探险。最直接的想法是维护一个已见营地的集合，每次探险统计其中未出现过的营地个数。瓶颈在于同一次探险里可能重复到访同一个新营地（如示例 3），若直接计数会把它重复统计。关键观察到：一旦某个营地被「发现」，它就应当立刻进入已见集合，这样同一探险内再次出现时集合已含有它，`add` 返回 `false`，自然只计一次。因此用哈希集合模拟「发现即记录」，一趟遍历即可。

<!-- thinking:end -->

首先将 `expeditions[0]` 中所有营地（按 `->` 分割后忽略空串）加入哈希集合 `known`，表示初始已知营地。

然后从 `i = 1` 开始遍历每次探险：对当前记录按 `->` 分割，遍历其中的营地，若营地非空且通过 `known.add(camp)` 成功加入集合（即之前未出现），说明发现新营地，将计数 `cnt` 加一。由于加入后同一探险内重复出现的营地会因已存在而不再计数，天然满足「每处新营地只算一次」。

维护 `bestIdx` 与 `bestCnt`，当 `cnt > bestCnt` 时更新。最终若 `bestCnt == 0`（没有任何新营地），返回 `-1`；否则返回 `bestIdx`。

时间复杂度 $O(\sum |expeditions[i]|)$，空间复杂度 $O(\sum |expeditions[i]|)$。其中 $|expeditions[i]|$ 为第 $i$ 次探险记录的字符串长度。

<!-- tabs:start -->

#### Java

```java
class Solution {
    public int adventureCamp(String[] expeditions) {
        Set<String> known = new HashSet<>();
        for (String camp : expeditions[0].split("->")) {
            if (!camp.isEmpty()) {
                known.add(camp);
            }
        }

        int bestIdx = -1;
        int bestCnt = 0;
        for (int i = 1; i < expeditions.length; i++) {
            int cnt = 0;
            for (String camp : expeditions[i].split("->")) {
                if (!camp.isEmpty() && known.add(camp)) {
                    cnt++;
                }
            }
            if (cnt > bestCnt) {
                bestCnt = cnt;
                bestIdx = i;
            }
        }
        return bestIdx;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

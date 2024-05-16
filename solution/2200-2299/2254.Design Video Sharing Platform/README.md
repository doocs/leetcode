---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2254.Design%20Video%20Sharing%20Platform/README.md
tags:
    - 栈
    - 设计
    - 哈希表
    - 有序集合
---

<!-- problem:start -->

# [2254. 设计视频共享平台 🔒](https://leetcode.cn/problems/design-video-sharing-platform)

[English Version](/solution/2200-2299/2254.Design%20Video%20Sharing%20Platform/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你有一个视频分享平台，用户可以上传和删除视频。每个 <code>video</code> 都是&nbsp;<strong>字符串&nbsp;</strong>类型的数字，其中字符串的第 <code>i</code> 位表示视频中第 <code>i</code> 分钟的内容。例如，第一个数字表示视频中第 <code>0</code> 分钟的内容，第二个数字表示视频中第 <code>1</code> 分钟的内容，以此类推。视频的观众也可以喜欢和不喜欢视频。该平台会跟踪每个视频的&nbsp;<strong>观看次数</strong>、<strong>点赞次数&nbsp;</strong>和 <strong>不喜欢次数</strong>。</p>

<p>当视频上传时，它与最小可用整数 <code>videoId</code> 相关联，<code>videoId</code> 从 <code>0</code> 开始的。一旦一个视频被删除，与该视频关联的 <code>videoId</code> 就可以用于另一个视频。</p>

<p>实现 <code>VideoSharingPlatform</code> 类:</p>

<ul>
	<li><code>VideoSharingPlatform()</code> 初始化对象。</li>
	<li><code>int upload(String video)</code> 用户上传一个 <code>video</code>. 返回与视频相关联的<code>videoId</code> 。</li>
	<li><code>void remove(int videoId)</code>&nbsp;如果存在与 <code>videoId</code> 相关联的视频，则删除该视频。</li>
	<li><code>String watch(int videoId, int startMinute, int endMinute)</code> 如果有一个视频与 <code>videoId</code>&nbsp;相关联，则将该视频的观看次数增加 <code>1</code>，并返回视频字符串的子字符串，从 <code>startMinute</code> 开始，以 <code>min(endMinute, video.length - 1</code><code>)</code>(含边界) 结束。否则，返回 <code>"-1"</code>。</li>
	<li><code>void like(int videoId)</code> 如果存在与 <code>videoId</code> 相关联的视频，则将与 <code>videoId</code> 相关联的视频的点赞数增加 <code>1</code>。</li>
	<li><code>void dislike(int videoId)</code> 如果存在与 <code>videoId</code> 相关联的视频，则将与 <code>videoId</code> 相关联的视频上的不喜欢次数增加 <code>1</code>。</li>
	<li><code>int[] getLikesAndDislikes(int videoId)</code> 返回一个长度为 <code>2</code> ，<strong>下标从 0 开始 </strong>的整型数组，其中 <code>values[0]</code>&nbsp;是与 <code>videoId</code>&nbsp;相关联的视频上的点赞数，<code>values[1]</code> 是不喜欢数。如果没有与&nbsp;<code>videoId</code> 相关联的视频，则返回 <code>[-1]</code>。</li>
	<li><code>int getViews(int videoId)</code> 返回与&nbsp;<code>videoId</code> 相关联的视频的观看次数，如果没有与 <code>videoId</code>&nbsp;相关联的视频，返回 <code>-1</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入</strong>
["VideoSharingPlatform", "upload", "upload", "remove", "remove", "upload", "watch", "watch", "like", "dislike", "dislike", "getLikesAndDislikes", "getViews"]
[[], ["123"], ["456"], [4], [0], ["789"], [1, 0, 5], [1, 0, 1], [1], [1], [1], [1], [1]]
<strong>输出</strong>
[null, 0, 1, null, null, 0, "456", "45", null, null, null, [1, 2], 2]

<strong>解释</strong>
VideoSharingPlatform videoSharingPlatform = new VideoSharingPlatform();
videoSharingPlatform.upload("123");          // 最小的可用 videoId 是 0，所以返回 0。
videoSharingPlatform.upload("456");          // 最小的可用 videoId 是 1，所以返回 1。
videoSharingPlatform.remove(4);              // 没有与 videoId 4 相关联的视频，所以什么都不做。
videoSharingPlatform.remove(0);              // 删除与 videoId 0 关联的视频。
videoSharingPlatform.upload("789");          // 由于与 videoId 0 相关联的视频被删除，
                                             // 0 是最小的可用 videoId，所以返回 0。
videoSharingPlatform.watch(1, 0, 5);         // 与 videoId 1 关联的视频为 "456"。
                                             // 从分钟 0 到分钟 min(5,3 - 1)= 2 的视频为 "456"，因此返回 "456"。
videoSharingPlatform.watch(1, 0, 1);         // 与 videoId 1 关联的视频为 "456"。
                                             // 从分钟 0 到分钟 min(1,3 - 1)= 1 的视频为 "45"，因此返回 "45"。
videoSharingPlatform.like(1);                // 增加与 videoId 1 相关的视频的点赞数。
videoSharingPlatform.dislike(1);             // 增加与 videoId 1 相关联的视频的不喜欢的数量。
videoSharingPlatform.dislike(1);             // 增加与 videoId 1 相关联的视频的不喜欢的数量。
videoSharingPlatform.getLikesAndDislikes(1); // 在与 videoId 1 相关的视频中有 1 个喜欢和 2 个不喜欢，因此返回[1,2]。
videoSharingPlatform.getViews(1);            // 与 videoId 1 相关联的视频有 2 个观看数，因此返回2。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入</strong>
["VideoSharingPlatform", "remove", "watch", "like", "dislike", "getLikesAndDislikes", "getViews"]
[[], [0], [0, 0, 1], [0], [0], [0], [0]]
<strong>输出</strong>
[null, null, "-1", null, null, [-1], -1]

<strong>解释</strong>
VideoSharingPlatform videoSharingPlatform = new VideoSharingPlatform();
videoSharingPlatform.remove(0);              // 没有与 videoId 0 相关联的视频，所以什么都不做。
videoSharingPlatform.watch(0, 0, 1);         // 没有与 videoId 0 相关联的视频，因此返回 "-1"。
videoSharingPlatform.like(0);                // 没有与 videoId 0 相关联的视频，所以什么都不做。
videoSharingPlatform.dislike(0);             // 没有与 videoId 0 相关联的视频，所以什么都不做。
videoSharingPlatform.getLikesAndDislikes(0); // 没有与 videoId 0 相关联的视频，因此返回 [-1]。
videoSharingPlatform.getViews(0);            // 没有视频与 videoId 0 相关联，因此返回 -1。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= video.length &lt;= 10<sup>5</sup></code></li>
	<li>调用&nbsp;<code>upload</code>&nbsp;时所有&nbsp;<code>video.length</code>&nbsp;的总和不会超过&nbsp;<code>10<sup>5</sup></code></li>
	<li><code>video</code> 由数字组成</li>
	<li><code>0 &lt;= videoId &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= startMinute &lt; endMinute &lt; 10<sup>5</sup></code></li>
	<li><code>startMinute &lt; video.length</code></li>
	<li>调用&nbsp; <code>watch</code>&nbsp;时所有&nbsp;<code>endMinute - startMinute</code>&nbsp;的总和不会超过&nbsp;<code>10<sup>5</sup></code>。</li>
	<li>所有函数&nbsp;<strong>总共&nbsp;</strong>最多调用 <code>10<sup>5</sup></code> 次。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

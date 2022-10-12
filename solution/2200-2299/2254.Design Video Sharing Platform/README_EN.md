# [2254. Design Video Sharing Platform](https://leetcode.com/problems/design-video-sharing-platform)

[中文文档](/solution/2200-2299/2254.Design%20Video%20Sharing%20Platform/README.md)

## Description

<p>You have a video sharing platform where users can upload and delete videos. Each <code>video</code> is a <strong>string</strong> of digits, where the <code>i<sup>th</sup></code> digit of the string represents the content of the video at minute <code>i</code>. For example, the first digit represents the content at minute <code>0</code> in the video, the second digit represents the content at minute <code>1</code> in the video, and so on. Viewers of videos can also like and dislike videos. Internally, the platform keeps track of the <strong>number of views, likes, and dislikes</strong> on each video.</p>

<p>When a video is uploaded, it is associated with the smallest available integer <code>videoId</code> starting from <code>0</code>. Once a video is deleted, the <code>videoId</code> associated with that video can be reused for another video.</p>

<p>Implement the <code>VideoSharingPlatform</code> class:</p>

<ul>
	<li><code>VideoSharingPlatform()</code> Initializes the object.</li>
	<li><code>int upload(String video)</code> The user uploads a <code>video</code>. Return the <code>videoId</code> associated with the video.</li>
	<li><code>void remove(int videoId)</code> If there is a video associated with <code>videoId</code>, remove the video.</li>
	<li><code>String watch(int videoId, int startMinute, int endMinute)</code> If there is a video associated with <code>videoId</code>, increase the number of views on the video by <code>1</code> and return the substring of the video string starting at <code>startMinute</code> and ending at <code>min(endMinute, video.length - 1</code><code>)</code> (<strong>inclusive</strong>). Otherwise, return <code>&quot;-1&quot;</code>.</li>
	<li><code>void like(int videoId)</code> Increases the number of likes on the video associated with <code>videoId</code> by <code>1</code> if there is a video associated with <code>videoId</code>.</li>
	<li><code>void dislike(int videoId)</code> Increases the number of dislikes on the video associated with <code>videoId</code> by <code>1</code> if there is a video associated with <code>videoId</code>.</li>
	<li><code>int[] getLikesAndDislikes(int videoId)</code> Return a <strong>0-indexed</strong> integer array <code>values</code> of length <code>2</code> where <code>values[0]</code> is the number of likes and <code>values[1]</code> is the number of dislikes on the video associated with <code>videoId</code>. If there is no video associated with <code>videoId</code>, return <code>[-1]</code>.</li>
	<li><code>int getViews(int videoId)</code> Return the number of views on the video associated with <code>videoId</code>, if there is no video associated with <code>videoId</code>, return <code>-1</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;VideoSharingPlatform&quot;, &quot;upload&quot;, &quot;upload&quot;, &quot;remove&quot;, &quot;remove&quot;, &quot;upload&quot;, &quot;watch&quot;, &quot;watch&quot;, &quot;like&quot;, &quot;dislike&quot;, &quot;dislike&quot;, &quot;getLikesAndDislikes&quot;, &quot;getViews&quot;]
[[], [&quot;123&quot;], [&quot;456&quot;], [4], [0], [&quot;789&quot;], [1, 0, 5], [1, 0, 1], [1], [1], [1], [1], [1]]
<strong>Output</strong>
[null, 0, 1, null, null, 0, &quot;456&quot;, &quot;45&quot;, null, null, null, [1, 2], 2]

<strong>Explanation</strong>
VideoSharingPlatform videoSharingPlatform = new VideoSharingPlatform();
videoSharingPlatform.upload(&quot;123&quot;);          // The smallest available videoId is 0, so return 0.
videoSharingPlatform.upload(&quot;456&quot;);          // The smallest available <code>videoId</code> is 1, so return 1.
videoSharingPlatform.remove(4);              // There is no video associated with videoId 4, so do nothing.
videoSharingPlatform.remove(0);              // Remove the video associated with videoId 0.
videoSharingPlatform.upload(&quot;789&quot;);          // Since the video associated with videoId 0 was deleted,
                                             // 0 is the smallest available <code>videoId</code>, so return 0.
videoSharingPlatform.watch(1, 0, 5);         // The video associated with videoId 1 is &quot;456&quot;.
                                             // The video from minute 0 to min(5, 3 - 1) = 2 is &quot;456&quot;, so return &quot;453&quot;.
videoSharingPlatform.watch(1, 0, 1);         // The video associated with videoId 1 is &quot;456&quot;.
                                             // The video from minute 0 to min(1, 3 - 1) = 1 is &quot;45&quot;, so return &quot;45&quot;.
videoSharingPlatform.like(1);                // Increase the number of likes on the video associated with videoId 1.
videoSharingPlatform.dislike(1);             // Increase the number of dislikes on the video associated with videoId 1.
videoSharingPlatform.dislike(1);             // Increase the number of dislikes on the video associated with videoId 1.
videoSharingPlatform.getLikesAndDislikes(1); // There is 1 like and 2 dislikes on the video associated with videoId 1, so return [1, 2].
videoSharingPlatform.getViews(1);            // The video associated with videoId 1 has 2 views, so return 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input</strong>
[&quot;VideoSharingPlatform&quot;, &quot;remove&quot;, &quot;watch&quot;, &quot;like&quot;, &quot;dislike&quot;, &quot;getLikesAndDislikes&quot;, &quot;getViews&quot;]
[[], [0], [0, 0, 1], [0], [0], [0], [0]]
<strong>Output</strong>
[null, null, &quot;-1&quot;, null, null, [-1], -1]

<strong>Explanation</strong>
VideoSharingPlatform videoSharingPlatform = new VideoSharingPlatform();
videoSharingPlatform.remove(0);              // There is no video associated with videoId 0, so do nothing.
videoSharingPlatform.watch(0, 0, 1);         // There is no video associated with videoId 0, so return &quot;-1&quot;.
videoSharingPlatform.like(0);                // There is no video associated with videoId 0, so do nothing.
videoSharingPlatform.dislike(0);             // There is no video associated with videoId 0, so do nothing.
videoSharingPlatform.getLikesAndDislikes(0); // There is no video associated with videoId 0, so return [-1].
videoSharingPlatform.getViews(0);            // There is no video associated with videoId 0, so return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= video.length &lt;= 10<sup>5</sup></code></li>
	<li>The sum of <code>video.length</code> over all calls to <code>upload</code> does not exceed <code>10<sup>5</sup></code></li>
	<li><code>video</code> consists of digits.</li>
	<li><code>0 &lt;= videoId &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= startMinute &lt; endMinute &lt; 10<sup>5</sup></code></li>
	<li><code>startMinute &lt; video.length</code></li>
	<li>The sum of <code>endMinute - startMinute</code> over all calls to <code>watch</code> does not exceed <code>10<sup>5</sup></code>.</li>
	<li>At most <code>10<sup>5</sup></code> calls <strong>in total</strong> will be made to all functions.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->

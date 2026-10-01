# Prompt: Orchestrate page-level translation

Mục tiêu của orchestration là dịch nhiều bài song song với **ownership ở cấp trang/bài**.

## Unit chuẩn

**1 agent = 1 bài = 1 source page hoàn chỉnh = 1 target page hoàn chỉnh.**

Ví dụ:

- Agent A: `solution/.../0004.../README_EN.md` → `vi/solution/.../0004.../README.md`
- Agent B: `solution/.../0005.../README_EN.md` → `vi/solution/.../0005.../README.md`

Không chia một bài thành các task kiểu:

- translate comments;
- translate headings;
- translate description;
- translate solution section;
- translate examples;
- review comments.

Các phần trên thuộc cùng ownership của agent đang dịch cả trang.

## Cách điều phối

1. Chọn N bài độc lập chưa dịch hoặc cần update.
2. Giao mỗi bài cho đúng một agent.
3. Mỗi agent đọc toàn bộ `README_EN.md` của bài được giao.
4. Agent tạo/update toàn bộ `README.md` tiếng Việt tương ứng.
5. Agent tự quick-review và chạy mechanical check cần thiết.
6. Khi xong, agent trả kết quả của chính bài đó.

Có thể chạy nhiều agent song song nếu các target file khác nhau.

## Không làm

- Không tạo subtask chỉ để dịch code comments.
- Không chia một trang thành nhiều agent nếu không có yêu cầu đặc biệt từ user.
- Không tạo source map/report/hash/state/reviewer ceremony cho mỗi bài.
- Không yêu cầu agent khác review lại mặc định.
- Không biến translation task thành audit pipeline.

## Code comments

Code và code comments mặc định giữ nguyên theo `translation/RULE.md`.
Không tạo agent riêng để dịch comments trong code.

## Output mong muốn

Mỗi agent hoàn tất **một trang tiếng Việt đầy đủ**, không phải một mảnh nội dung của trang.

Nếu user yêu cầu 20 agents thì mặc định hiểu là **20 bài khác nhau chạy song song**, không phải 20 agents chia nhau một bài.

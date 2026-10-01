# Translation Instructions — Fast Workflow

## Read set tối thiểu

Trước khi dịch, đọc:

1. `translation/RULE.md`;
2. `translation/PROJECT_RULES.md`;
3. `translation/GLOSSARY.md`;
4. `README_EN.md` của bài đang làm.

Không cần đọc governance hoặc audit pack cũ.

## Workflow một bài

### 1. Đọc source

Đọc toàn bộ `README_EN.md` để hiểu đề bài, solution, complexity và các phần phải giữ nguyên.

### 2. Dịch

Tạo hoặc update file target tương ứng trong `vi/`. Dịch prose theo `RULE.md`: đúng meaning, câu tự nhiên, không cố Việt hóa mọi technical term.

### 3. Quick review

Đối chiếu source và target một lượt, tập trung vào thiếu ý, condition/negation, algorithm, complexity, code, math, URL, example data và các câu quá literal.

Không cần coverage map hay report.

### 4. Mechanical checks

```bash
pnpm exec prettier --write "<target README.md>"
python3 translation/tools/check_vi.py "<target README.md>"
python3 scripts/check_thinking.py "<target README.md>"
```

Warning về English residue chỉ là tín hiệu review, không có nghĩa mọi English term phải được dịch.

### 5. Hoàn tất

Nếu check pass và quick review ổn thì file đã hoàn tất. Không tạo unit report, hash hoặc state sau mỗi file.

## Khi nào cần review sâu

Chỉ tăng effort khi source mơ hồ, câu technical khó, cấu trúc bất thường, check fail, upstream thay đổi đáng kể hoặc người dùng yêu cầu audit riêng.

## Batch translation

- mỗi file chỉ có một writer tại một thời điểm;
- các bài độc lập có thể chạy song song;
- mỗi bài dùng workflow 5 bước trên;
- không dừng batch để cập nhật progress metadata;
- Git history và file `vi/**/README.md` đủ cho phần lớn tracking.

# Prompt: tiếp tục sau session/compaction

Đọc lại `translation/INSTRUCTIONS.md` và read set hiện hành. Mở source map, assignment/report gần nhất và thay đổi thực trong workspace. Không dùng tóm tắt hội thoại hoặc lời báo “đã xong” làm nguồn nội dung hay bằng chứng tiến độ.

Xác minh snapshot nguồn, target hash, policy/glossary version, owner còn hiệu lực và unit đang dở. Nếu hash/rule thay đổi, đánh giá stale và phạm vi phải review lại trước khi tiếp tục. Không overwrite phần sửa chưa biết của người dùng.

Đọc nguyên văn source của semantic block đang tiếp tục và context cần thiết. Khôi phục từ block boundary đã ghi trong report, không từ phần trăm hoặc số token ước tính. Kiểm tra đầu/cuối phần đã dịch để tránh lặp hoặc bỏ đoạn.

Tiếp tục workflow unit với cùng gate chất lượng. Chỉ báo trạng thái dựa trên file và evidence hiện tại; không kế thừa PASS cũ cho một target đã đổi.

# Regression suite cho hành vi tuân thủ

[regression-cases.yaml](regression-cases.yaml) là bộ tình huống đầu vào và expected behavior để kiểm tra một agent đã nạp pack. Đây **không phải** report rằng các model đã vượt qua toàn bộ case.

## Cách thực hiện trên runtime đích

Tạo workspace/snapshot nhỏ dùng dữ liệu thử, không chạy trên repo thật hoặc đưa secret vào test. Với mỗi case, nạp core và đúng module, dựng precondition từ fixture rồi giao task. Ghi model/runtime/version, read set hash, task input, output/diff, tool trace có thể chia sẻ và verdict. Không yêu cầu agent công khai chain-of-thought.

Reviewer đối chiếu expected behavior và bằng chứng thực, không dùng việc agent tự nói “tuân thủ” làm pass. Case về ghi file/Git/concurrency cần runtime trace hoặc diff, không thể xác minh chỉ qua câu trả lời. Case về chất lượng dịch cần source-vs-target review, không thể xác minh bằng regex đơn thuần.

Chạy lại case bị ảnh hưởng khi đổi core/context policy, và một lượt regression đầy đủ trước khi áp base mới lên nhiều project. Một lần pass không đảm bảo agent luôn tuân thủ ở mọi context; có thể chạy nhiều lần với context dài/ngắn để kiểm tra độ ổn định.

## Verdict

`pass`: mọi yêu cầu của case có evidence. `fail`: ít nhất một yêu cầu bị vi phạm. `not_run`: chưa chạy hoặc runtime không có capability cần thiết; không tính vào pass. Không gộp not_run thành pass bằng cách giảm mẫu số âm thầm.

Báo rõ số case pass/fail/not_run, loại lỗi và evidence. Semantic case phải ghi phạm vi review. Không dùng một điểm trung bình để che lỗi bịa/mất nội dung, corrupt code hoặc ghi trái phép.

## Phân biệt với kiểm tra gói

Kiểm tra file/link/YAML/checksum/ZIP chỉ xác minh cấu trúc gói, không xác minh hành vi model. Kết quả kiểm tra gói nằm ở `PACKAGE_VALIDATION.md` tại root; không đổi các expected behavior dưới đây thành “đã test agent” khi chưa thực thi.

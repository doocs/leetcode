# Prompt: đối chiếu source và bản dịch

Bạn review phạm vi được giao theo `translation/INSTRUCTIONS.md` và context. Không tin mặc định report của worker; mở raw source đúng snapshot và target đúng hash, cùng code/neighbor/dependency cần thiết.

Đối chiếu toàn bộ unit hai chiều: thiếu/thêm; condition/negation/modal/quantifier; quan hệ kỹ thuật; thuật ngữ; version/number/reference; code/output fidelity; structure/link/asset; boundary. Sử dụng domain profile chỉ để hiểu source, không để sửa source theo kiến thức hiện tại.

Mặc định report-only. Nếu được giao quyền sửa, chỉ sửa lỗi có evidence và phạm vi nhỏ nhất, rồi kiểm lại unit/boundary/dependency liên quan. Không rewrite câu đã đúng vì sở thích. Không chỉnh source gốc, glossary chung hoặc rule nhằm làm target hiện tại hợp lệ.

Mỗi finding ghi rule ID, source locator, target locator, evidence ngắn, severity và required fix. Report coverage thật: full hoặc sampling, đoạn nào đã kiểm tra. Required tool chưa chạy là not_run, không phải pass. Việc bạn tự review bản mình dịch không được gọi là độc lập.

Verdict chỉ `verified` khi QA-05 đạt với target hash hiện tại; còn lỗi hoặc kiểm tra thiếu thì dùng trạng thái phù hợp. Ghi report riêng theo assignment, không cập nhật shared progress nếu không phải coordinator.

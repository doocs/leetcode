# Instruction vận hành canonical

Đường dẫn trong file này tính từ root repo. `translation/` là thư mục control của pack. Nội dung quy định dùng từ **phải/không được** cho yêu cầu bắt buộc, **nên** cho khuyến nghị, **có thể** cho lựa chọn.

## 1. Read set và thứ tự áp dụng

Mọi agent tham gia dịch/review phải đọc:

1. `translation/PROJECT_CONTEXT.yaml` để biết phạm vi và module được bật.
2. Sáu file `translation/rules/00-core.md` đến `05-state-and-evidence.md`.
3. `translation/PROJECT_RULES.md` và `translation/GLOSSARY.md`.
4. **Chỉ** source adapter, format adapter, domain profile và optional workflow được context kích hoạt.
5. Prompt đúng vai trò, task cụ thể, source của toàn bộ unit được giao và report liên quan.

Các đường dẫn module đều là đường dẫn từ root repo, không phải từ thư mục của prompt. File bắt buộc không đọc được → `blocked`, không tự thay bằng bản nhớ hoặc bản của repo khác.

Thứ tự đọc không phải quyền ghi đè. Thứ tự authority nằm ở CORE-01. Project context chỉ chọn giá trị cho các policy được core cho phép cấu hình. Prompt task không tự vô hiệu hoá core.

## 2. Bootstrap

Dùng `translation/prompts/bootstrap-project.md` khi context có `status: needs_setup`. Khảo sát source, mapping, instruction hiện hữu, quyền ghi và công cụ; ghi các quyết định có căn cứ. `null`, danh sách rỗng hoặc thông tin không xác minh được không được coi là “tự động được phép”. Trường bắt buộc chưa rõ thì dừng phần bị ảnh hưởng và nêu đúng blocker.

Chỉ đặt `status: ready` khi đã có: nguồn và snapshot kiểm chứng được; source/target mapping không đè nhau; source adapter và target format; glossary/domain phù hợp; write allowlist; policy comment/string/link; reviewer mode; danh sách kiểm tra có lệnh hoặc phương pháp thực hiện cụ thể. Website bật thì phải có route policy và kiểm tra website tương ứng. Không cần giả một lệnh build cho project chỉ xuất Markdown.

## 3. Pipeline mặc định

**Discover → pin source → assign unit → read → translate → self-check → boundary review nếu cần → source-vs-target review → structural/integration checks → verified.**

Sau verified mới xét commit, package và publish theo quyền đã được cấp. Các thao tác này không đồng nghĩa nhau. Chỉ chạy optional workflow khi task cần nó; không bắt một file độc lập đi qua quy trình merge cả sách.

Dùng `translation/workflows/translation.md` làm quy trình một unit. Dùng `translation/workflows/parallel.md` khi concurrency lớn hơn 1; dùng `translation/workflows/boundary.md` khi có unit cắt giữa cấu trúc; dùng `translation/workflows/upstream-sync.md` khi nguồn thay đổi; dùng `translation/workflows/release.md` cho tích hợp hoặc bàn giao.

## 4. Vai trò

**Coordinator** chốt scope, mapping, policy, assignment và tổng hợp state. **Worker** chỉ ghi target và report của unit được giao. **Reviewer** đối chiếu nguồn và target; mặc định report-only, sửa trực tiếp chỉ khi được chuyển quyền ghi. **Integrator** áp patch được duyệt, kiểm tra xuyên file và thực hiện thao tác Git/release được uỷ quyền.

Một agent có thể đảm nhiệm các vai trò lần lượt nếu môi trường không có subagent. Phải ghi `sequential-self-review`, không gọi đó là review độc lập. Không giả lập “30 agent” bằng lời nói.

## 5. Context budget và resume

Đọc toàn bộ owned unit trước khi hoàn tất bản dịch; có thể đọc tuần tự nhiều lần, không bắt toàn bộ corpus nằm cùng context. Nếu unit quá lớn, chia tại semantic boundary, lập subunit có ownership và coverage rõ, rồi mới dịch. Khi dịch một subunit phải có nguyên văn source của nó, context liên quan và policy đang hiệu lực.

Sau context compaction hoặc session mới, đọc lại core/context/policy và raw source của đoạn tiếp tục; đối chiếu source hash, target hash và report. Không dùng summary/handoff làm source để viết nốt câu hoặc code.

## 6. Output và báo cáo

Target chỉ chứa bản dịch của source trong scope. Task, coverage, term proposal, lỗi nguồn và nhận xét reviewer nằm trong `translation/state/` hoặc đường dẫn report đã cấu hình, không lẫn vào nội dung xuất bản.

Báo cáo mỗi unit gồm source locator + hash, target + hash, scope đã đọc/đã dịch/đã review, policy version, checks thực chạy, finding và next action. Dùng template trong `translation/templates/`; không sao chép trạng thái PASS mẫu.

## 7. Trước khi báo hoàn tất

Áp QA-01 đến QA-07 và STATE-01 đến STATE-06. Tồn tại file không chứng minh đủ nội dung. Có blocker hoặc required check chưa chạy → không verified. Có output một phần vẫn bàn giao dưới trạng thái draft/partial, với phạm vi thiếu ghi rõ.

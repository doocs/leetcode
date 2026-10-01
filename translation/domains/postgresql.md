# Domain profile: PostgreSQL / SQL

Chỉ nạp khi context chọn profile này. Không chọn version PostgreSQL từ trí nhớ hoặc bản docs hiện hành; version/context nguồn đã pin là authority.

## Terminology baseline

| Nhóm        | Cách dùng English-first khi là concept                                                                       |
| ----------- | ------------------------------------------------------------------------------------------------------------ |
| Model       | database, database cluster, schema, table, row, tuple, column, data type, domain                             |
| SQL         | query, statement, clause, expression, predicate, subquery, CTE, window function, aggregate                   |
| Transaction | transaction, commit, rollback, savepoint, isolation level, snapshot, MVCC, lock                              |
| Storage     | page, block, heap, TOAST, tablespace, WAL, checkpoint, vacuum, statistics                                    |
| Planner     | query planner, execution plan, cost, cardinality, selectivity, index scan, index-only scan, bitmap heap scan |
| Operation   | role, privilege, session, connection, replication, primary, standby, failover, backup                        |

`table of contents` vẫn là mục lục; `role of the planner` không phải PostgreSQL role. Tên catalog, config, keyword/API như `pg_stat_activity`, `work_mem`, `EXPLAIN ANALYZE` và `psql` giữ nguyên.

## Protected content

SQL, DDL/DML, PL/pgSQL, shell/meta-command, prompt `psql`, config, result set, log/error và query plan phải theo CODE. `EXPLAIN` tree giữ node, arrow, indentation, cost/rows/width, actual time/loops/buffers và planning/execution time. Không chuyển plan thành prose/table.

Kết quả SQL là data/output, không dịch cell như bảng giải thích. Giữ `NULL`, boolean, decimal, unit và precision; không sửa số theo một lần chạy mới. Không đổi `SERIAL` thành identity hoặc command thành cú pháp mới hơn.

## Semantic traps cần đối chiếu

Database/cluster/schema; role/user; row/tuple; session/connection/process; snapshot/backup; lock/isolation; MVCC visibility/physical existence; VACUUM/ANALYZE; planner estimate/actual runtime; constraint/index; index scan/index-only scan; physical/logical replication; function/procedure; view/materialized view; NULL và three-valued logic; sequence/SERIAL/identity.

Kiểm tra các distinction từ source, không tự thêm bài giảng giải thích chúng vào target. Source sai/nghi lỗi ghi report riêng. Profile này không bắt một tài liệu SQL chung tuân syntax PostgreSQL nếu source dùng dialect khác; chọn profile riêng khi khác dialect.

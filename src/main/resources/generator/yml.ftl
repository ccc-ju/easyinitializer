# oss配置
oss:
  accessSecret: ENC(ys+mbFnF71afPL22YUWw0P0B/RSRMh8vgOswK7KhFIq5tq3/SAZQQg==)
  accessKey: ENC(NaC4iCnLX+bUXi5ifbwjJQXenTTsKE5xpt7QXQs0nMPxzCfgxzQfVQ==)
  # 文件过期时间 3600 * 1000 默认1个小时 (毫秒)
  expireMilliSecond: 3600000

spring:
  # 防止 Knife4j 2.0及以上版本访问报错
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher

jasypt:
  encryptor:
    # 自定义加密盐值(密钥)
    password: shendu188
    # 加密算法设置
    algorithm: PBEWithMD5AndDES
    iv-generator-classname: org.jasypt.iv.NoIvGenerator
package com.example.springsecurityjwt.user.record;

import java.util.List;

public record UserRoleRecord(String userId, List<String> roleIds) {
}

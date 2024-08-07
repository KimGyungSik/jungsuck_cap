package com.firstSpring.dao.user;

import com.firstSpring.domain.user.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired SqlSession session;

    private static final String namespace = "com.firstSpring.dao.user.UserDao.";

    // 테스트용 delete 문
    @Override
    public int deleteTestUser(String id) throws Exception {
        return session.delete(namespace + "deleteTestUser", id);
    }

    // 테스트용 deleteAll 문
    @Override
    public int deleteAllTestUser() throws Exception {
        return session.delete(namespace + "deleteAllTestUser");
    }


    // 회원가입한 고객의 수
    @Override
    public int countUser() throws Exception {
        return session.selectOne(namespace + "countUser");
    }

    // 가입 상태의 고객의 수
    @Override
    public int countActiveUser() throws Exception {
        return session.selectOne(namespace + "countActiveUser");
    }

    // 회원가입한 전체 고객 조회
    @Override
    public List<UserDto> selectUserAll() throws Exception {
        return session.selectList(namespace + "selectUserAll");
    }

    // 탈퇴 회원을 제외한, 가입상태 'Y' 인 모든 고객 조회
    @Override
    public List<UserDto> selectActiveUserAll() throws Exception {
        return session.selectList(namespace + "selectUserAll");
    }

    // SELECT
    // 1. 로그인
    // 1.1. 아이디로 식별된 특정 고객 조회
    // 1.1.1. view 에서 입력한 id 를 매개변수로 받고 UserDto 인스턴스에 고객 정보 담아서 반환
    @Override
    public UserDto selectUser(String id) throws Exception {
        return session.selectOne(namespace + "selectUser", id);
    }

    // INSERT
    // 2. 회원가입
    // 2.1. 아이디, 비밀번호, 이름, 생년월일, 성별, 전화번호, 휴대폰 번호, 이메일, 가입일, 가입상태, 최초등록일, 최초등록자, 최종수정일, 최종수정자 데이터 추가
    // 2.1.2. view 에서 입력한 고객 정보를 UserDto 객체로 받아서 처리하고, 따로 특정 반환 사항 없이 실행 됐으면 1 반환
    @Override
    public int insertUser(UserDto userDto) throws Exception {
        return session.insert(namespace + "insertUser", userDto);
    }

    // 2.2. 전체 가입고객(탈퇴회원 포함) 중 특정 아이디를 가진 고객 존재여부 확인
    @Override
    public UserDto selectAllUserId(String id) throws Exception {
        return session.selectOne(namespace + "selectAllUserId", id);
    }

    // UPDATE
    // 3. 이메일 인증
    // 3.1. 이메일 인증번호 받을 때마다 mail_key 컬럼에 저장
    // 3.1.1. 아이디/비밀번호 찾기 본인 인증 시 사용
    @Override
    public int updateMailKey(UserDto userDto) throws Exception {
        return session.update(namespace + "updateMailKey", userDto);
    }

    // 4. 아이디/비밀번호 찾기

    // SELECT
    // 4.1. 아이디 찾기
    // 4.1.1. 이름 및 이메일로 식별된 특정 회원의 아이디 조회
    @Override
    public UserDto selectUserId(UserDto userDto) throws Exception {
        return session.selectOne(namespace + "selectUserId", userDto);
    }

    // UPDATE
    // 4.2. 비밀번호 찾기
    // 4.2.1. 비밀번호 찾기 시 새로운 비밀번호로 변경
    @Override
    public int updateUserPwd(String id, String pwd) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("pwd", pwd);
        return session.update(namespace + "updateUserPwd", map);
    }

    // UPDATE
    // 5. 회원정보 변경
    // 5.1. 이메일 변경
    @Override
    public int updateUserEmail(String id, String email) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("email", email);
        return session.update(namespace + "updateUserEmail", map);
    }

    // 5.2. 생년월일 변경
    @Override
    public int updateUserBefBirth(String id, Integer bef_birth) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("bef_birth", bef_birth);
        return session.update(namespace + "updateUserBefBirth", map);
    }

    // 5.3. 핸드폰 번호 변경
    @Override
    public int updateUserMobileNum(String id, Integer mobile_num) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("mobile_num", mobile_num);
        return session.update(namespace + "updateUserMobileNum", map);
    }

    // UPDATE
    // 6. 회원탈퇴
    // 6.1. 회원가입 상태를 Y -> N 으로 변경
    @Override
    public int updateUserState(String id) throws Exception {
        return session.update(namespace + "updateUserState", id);
    }

}
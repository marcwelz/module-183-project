package ch.bbw.pr.sospri.service;

import ch.bbw.pr.sospri.model.Member;
import ch.bbw.pr.sospri.model.MemberToUserMapper;
import ch.bbw.pr.sospri.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@Slf4j
public class ModeratorService {
    @Autowired
    private MemberRepository repository;

    public Iterable<Member> getAll(){
        return repository.findAll();
    }

    public void add(Member member) {
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(10, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        repository.save(member);
    }

    public void update(Long id, Member member) {
        //save geht auch für update.
        repository.save(member);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Member getById(Long id) {
        Iterable<Member> memberitr = repository.findAll();

        for(Member member: memberitr){
            if (member.getId() == id) {
                return member;
            }
        }
        log.info("MemberService:getById(), id does not exist in repository: " + id);
        return null;
    }

    public Member getByUserName(String username) {
        Iterable<Member> memberitr = repository.findAll();
        memberitr.forEach(System.out::println);

        for (Member member : memberitr) {
            if (member.getUsername().equals(username)) {
                return member;
            }
        }
        log.info("MemberService:getByUserName(), username does not exist in repository: " + username);
        return null;
    }
}

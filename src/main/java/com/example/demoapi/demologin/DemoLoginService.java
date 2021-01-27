package com.example.demoapi.demologin;

import com.example.demoapi.exception.ErrorCode;
import com.example.demoapi.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoLoginService {

    private final DemoLoginRepository demoLoginRepository;
    private final ModelMapper modelMapper;

//    @Transactional
    public void login(DemoLoginDto demoLoginDto) {
        Optional<DemoLogin> checkUserName = Optional.of(new DemoLogin());
        Optional<DemoLogin> checkPassword = Optional.of(new DemoLogin());

        try {
            checkUserName = Optional.ofNullable(this.demoLoginRepository.checkUserName(demoLoginDto)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.ERR_NOT_FOUND.code, "userName Not Found")));

            checkPassword = Optional.ofNullable(this.demoLoginRepository.checkPassword(demoLoginDto)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.ERR_NOT_FOUND.code, "password invalid")));

            if(checkUserName.get().getCountLogin() != 3 && checkPassword.isPresent()){
                this.demoLoginRepository.loginSuccess(demoLoginDto);
                log.info("login success");
            }

            if(checkUserName.get().getCountLogin() == 3 ){
                throw new NotFoundException(ErrorCode.ERR_NOT_FOUND.code, "User Logged");
            }

        } catch (NotFoundException e) {
            if(e.getMessage().equals("password invalid")){
                if(checkUserName.get().getCountLogin() == 3 ){
                    log.info("User Logged");
                    throw new NotFoundException(ErrorCode.ERR_NOT_FOUND.code, "User Logged");
                }
                log.info("updateCountLogin");
                this.demoLoginRepository.updateCountLogin(demoLoginDto);
            }

            throw new NotFoundException(e.getMessage());
        }


//        Optional<DemoLogin> checkUserName = Optional.ofNullable(this.demoLoginRepository.checkUserName(demoLoginDto)
//                .orElseThrow(() -> new NotFoundException(ErrorCode.ERR_NOT_FOUND.code, "userName invalid")));
//
//        if(checkUserName.isPresent()){
//            if(checkUserName.get().getCountLogin() != 3){
//
//                Optional<DemoLogin> checkPassword = this.demoLoginRepository.checkPassword(demoLoginDto);
//
//                if(checkPassword.isEmpty()){
//                    int updateCountLogin = this.demoLoginRepository.updateCountLogin(demoLoginDto);
//
//                    log.info("updateCountLogin : {}", updateCountLogin);
//
//                    if(updateCountLogin == 0){
//                        throw new NotFoundException(ErrorCode.ERR_NOT_FOUND.code, "password invalid");
//                    }
//                } else {
//                    this.demoLoginRepository.loginSuccess(demoLoginDto);
//                    log.info("login success");
//                }
//            } else {
//                log.info("User Logged");
//                throw new NotFoundException(ErrorCode.ERR_NOT_FOUND.code, "User Logged");
//            }
//        }
    }

}

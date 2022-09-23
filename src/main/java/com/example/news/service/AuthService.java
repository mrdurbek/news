package com.example.news.service;

import com.example.news.dto.RegisterDTO;
import com.example.news.dto.UserDTO;
import com.example.news.entity.Lavozim;
import com.example.news.entity.User;
import com.example.news.exceptions.ResourceNdException;
import com.example.news.repositry.LavozimRepository;
import com.example.news.repositry.UserRepository;
import com.example.news.response.ApiResponse;
import com.example.news.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LavozimRepository lavozimRepository;

    public ApiResponse registerUser(RegisterDTO registerDTO) {
        if (registerDTO.getPassword().equals(registerDTO.getPrePassword())) {
            if (userRepository.existsByUsername(registerDTO.getUsername())) {
                User user = new User(registerDTO.getFullname(), registerDTO.getUsername(), passwordEncoder.encode(registerDTO.getPassword()),
                        lavozimRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNdException("assa")),true);

                userRepository.save(user);
                return new ApiResponse("Muvaffaqiyatli ro'yxatdan o'tdingiz", true);
            } else {
                return new ApiResponse("Bunday user ro'yxatdan o'tgan", false);
            }
        } else {
            return new ApiResponse("password takrori bir xil emas", false);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new UsernameNotFoundException("Bunday username topilmadi "));
    }

    public ApiResponse addUser(UserDTO userDTO) {
        Optional<User> optional = userRepository.findByUsername(userDTO.getUsername());
        if (!optional.isPresent()){
            Optional<Lavozim> optional1 = lavozimRepository.findById(userDTO.getLavozim_id());
            if (optional1.isPresent()){
                User user=new User(userDTO.getFullname(), userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()), optional1.get(), userDTO.isEnabled());
                userRepository.save(user);
                return new ApiResponse("User qo'shildi",true);
            }else {
                return new ApiResponse("Bunday lavozim topilmadi",false);
            }
        }else {
            return new ApiResponse("Bunday user mavjud ",false);
        }
    }

    public ApiResponse editUser(Long id, UserDTO userDTO) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()){
            Optional<User> optional1 = userRepository.findByUsername(userDTO.getUsername());
            if (!optional1.isPresent()){
                Optional<Lavozim> optional2 = lavozimRepository.findById(userDTO.getLavozim_id());
                if (optional2.isPresent()){
                    User user=optional.get();
                    user.setFullname(userDTO.getFullname());
                    user.setUsername(user.getUsername());
                    user.setPassword(user.getPassword());
                    user.setLavozim(optional2.get());
                    user.setEnabled(userDTO.isEnabled());
                    userRepository.save(user);
                    return new ApiResponse("Yangilandi",true);
                }else {
                    return new ApiResponse("Bunday lavozim topilmadi",false);
                }
            }else {
                return new ApiResponse("Bunday user mavjud ",false);
            }
        }else {
            return  new ApiResponse("Bunday user topilmadi",false);
        }
    }

    public ApiResponse deleteUser(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()){
            userRepository.deleteById(id);
            return new ApiResponse("O'chirildi",true);
        }else {
            return new ApiResponse("Bunday user topilmadi",false);
        }
    }
}

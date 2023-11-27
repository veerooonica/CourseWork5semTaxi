package com.bus.cont.main;

import com.bus.models.Users;
import com.bus.repo.RepoReserves;
import com.bus.repo.RepoRouteDate;
import com.bus.repo.RepoRouters;
import com.bus.repo.RepoUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Main {

    @Autowired
    protected RepoUsers repoUsers;
    @Autowired
    protected RepoRouters repoRouters;
    @Autowired
    protected RepoRouteDate repoRouteDate;
    @Autowired
    protected RepoReserves repoReserves;

    protected Users getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return repoUsers.findByUsername(userDetail.getUsername());
        }
        return null;
    }

    protected String getRole() {
        Users user = getUser();
        if (user != null) return user.getRole().toString();
        return "NOT";
    }

}
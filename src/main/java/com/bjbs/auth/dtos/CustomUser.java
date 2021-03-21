package com.bjbs.auth.dtos;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.io.iona.core.data.annotations.WithModelID;

/**
 * Created By Aristo Pacitra
 */

@WithModelID
public class CustomUser extends User {

    private final long userId;
    private final int applicationId;
    private final String userRealName;
    private final int titleId;
    private final String titleCode;
    private final int refDivisionId;
    private final String divisionName;
    private final int refLembagaId;
    private final String namaLembaga;

    public CustomUser(String username, String password, String userRealName,
                      Collection<? extends GrantedAuthority> authorities, long userId, int applicationId, int titleId,
                      String titleCode, int refDivisionId, String divisionName, int refLembagaId, String namaLembaga) {
        super(username, password, authorities);
        this.userId = userId;
        this.applicationId = applicationId;
        this.userRealName = userRealName;
        this.titleId = titleId;
        this.titleCode = titleCode;
        this.refDivisionId = refDivisionId;
        this.divisionName = divisionName;
        this.refLembagaId = refLembagaId;
        this.namaLembaga = namaLembaga;

    }

	public long getUserId() {
		return userId;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public String getUserRealName() {
		return userRealName;
	}

	public int getTitleId() {
		return titleId;
	}

	public String getTitleCode() {
		return titleCode;
	}

	public int getRefDivisionId() {
		return refDivisionId;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public int getRefLembagaId() {
		return refLembagaId;
	}

	public String getNamaLembaga() {
		return namaLembaga;
	}
	
}
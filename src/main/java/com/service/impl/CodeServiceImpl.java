package com.service.impl;

import com.dao.CodeDao;
import com.entity.Code;
import com.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private final CodeDao codeDao;

    public CodeServiceImpl(CodeDao codeDao) {
        this.codeDao = codeDao;
    }

    @Transactional
    @Override
    public void addCode(Code code) {
        codeDao.addCode(code);
    }

    @Transactional
    @Override
    public Optional<Code> getCode(Long id) {
        return codeDao.getCode(id);
    }
}
